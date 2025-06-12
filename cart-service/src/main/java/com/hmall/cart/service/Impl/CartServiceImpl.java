package com.hmall.cart.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.client.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.cart.config.CartProperties;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.CollUtils;
import com.hmall.common.utils.UserContext;
import com.hmall.cart.domain.dto.CartFormDTO;
import com.hmall.cart.domain.po.Cart;
import com.hmall.cart.domain.vo.CartVO;
import com.hmall.cart.mapper.CartMapper;
import com.hmall.cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

//    //旧版使用nacos进行负载均衡的代码
//    private final RestTemplate restTemplate;
//
//    //注入discovery客户端，用来查询nacos服务列表
//    private  final DiscoveryClient discoveryClient;

    private final ItemClient itemClient;   //使用OpenFegin工具简化请求过程代码，实现负载均衡(这里是动态代理实现的，bean在hm-api包中，要在启动项配置扫描)

    private final CartProperties cartProperties;

    @Override
    public void addItem2Cart(CartFormDTO cartFormDTO) {
        // 1.获取登录用户
        Long userId = UserContext.getUser();

        // 2.判断是否已经存在
        if(checkItemExists(cartFormDTO.getItemId(), userId)){
            // 2.1.存在，则更新数量
            baseMapper.updateNum(cartFormDTO.getItemId(), userId);
            return;
        }
        // 2.2.不存在，判断是否超过购物车数量
        checkCartsFull(userId);

        // 3.新增购物车条目
        // 3.1.转换PO
        Cart cart = BeanUtils.copyBean(cartFormDTO, Cart.class);
        // 3.2.保存当前用户
        cart.setUserId(userId);
        // 3.3.保存到数据库
        save(cart);
    }

    @Override
    public List<CartVO> queryMyCarts() {
        // 1.查询我的购物车列表
        List<Cart> carts = lambdaQuery().eq(Cart::getUserId, UserContext.getUser()).list();   ///这里使用了ThreadLocal来获取Common中prehandler中抽出的User信息
        if (CollUtils.isEmpty(carts)) {
            return CollUtils.emptyList();
        }

        // 2.转换VO
        List<CartVO> vos = BeanUtils.copyList(carts, CartVO.class);

        // 3.处理VO中的商品信息
        handleCartItems(vos);

        // 4.返回
        return vos;
    }

    /// 目前不实现
    private void handleCartItems(List<CartVO> vos) {
        // 1.获取商品id
        Set<Long> itemIds = vos.stream().map(CartVO::getItemId).collect(Collectors.toSet());

        /// 旧版使用传统代码实现负载均衡的代码
//        // 2.查询商品
//        //2.1.根据服务名称获取服务实例(拉取服务列表)
//        List<ServiceInstance> instances = discoveryClient.getInstances("item-service");
//        if(CollUtils.isEmpty(instances)){
//            return;
//        }
//        //2.2.手写负载均衡，从实例表中挑选一个实例
//        ServiceInstance instance=instances.get(RandomUtil.randomInt(instances.size()));
//        //这里是旧版的
//        ResponseEntity<List<ItemDTO>> response = restTemplate.exchange(
//                instance.getUri()+"/items?ids={ids}",     //实现从实例中获取服务的uri
//                HttpMethod.GET,
//                null,
//                //这个参数是返回值类型，现在我们需要返回一整个列表，但是list并不是网络请求参数的泛型，因此用ParameterizedTypeReference将其打包成对象
//                new ParameterizedTypeReference<List<ItemDTO>>() {},
//                Map.of("ids", CollUtils.join(itemIds, ",")) //这里使用hutool包中的字符串拼接方法
//        );
//        //解析响应
//        //s首先查看响应码是否是200（是否成功）
//        if(!response.getStatusCode().is2xxSuccessful()){
//            //查询失败，直接结束
//            return;
//        }
//        List<ItemDTO> items = response.getBody();

        /// 新版使用openfegin实现负载均衡的代码,一行代码搞定了
        List<ItemDTO> items= itemClient.queryItemByIds(itemIds);

        //判断结果是否为空
        if (CollUtils.isEmpty(items)) {
            return;
        }
        // 3.转为 id 到 item的map
        Map<Long, ItemDTO> itemMap = items.stream().collect(Collectors.toMap(ItemDTO::getId, Function.identity()));
        // 4.写入vo
        for (CartVO v : vos) {
            ItemDTO item = itemMap.get(v.getItemId());
            if (item == null) {
                continue;
            }
            v.setNewPrice(item.getPrice());
            v.setStatus(item.getStatus());
            v.setStock(item.getStock());
        }
    }

    @Override
    public void removeByItemIds(Collection<Long> itemIds) {
        // 1.构建删除条件，userId和itemId
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.lambda()
                .eq(Cart::getUserId, UserContext.getUser())
                .in(Cart::getItemId, itemIds);
        // 2.删除
        remove(queryWrapper);
    }

    private void checkCartsFull(Long userId) {
        int count = lambdaQuery().eq(Cart::getUserId, userId).count();
        if (count >= cartProperties.getMaxItems()) {
            throw new BizIllegalException(StrUtil.format("用户购物车课程不能超过{}", 10));
        }
    }

    private boolean checkItemExists(Long itemId, Long userId) {
        int count = lambdaQuery()
                .eq(Cart::getUserId, userId)
                .eq(Cart::getItemId, itemId)
                .count();
        return count > 0;
    }
}
