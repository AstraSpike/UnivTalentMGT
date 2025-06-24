package com.hmall.user.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.common.exception.BadRequestException;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.exception.ForbiddenException;
import com.hmall.common.utils.UserContext;
import com.hmall.user.config.JwtProperties;
import com.hmall.user.domain.dto.LoginFormDTO;
import com.hmall.user.domain.po.User;
import com.hmall.user.domain.vo.UserLoginVO;
//import com.hmall.user.enums.UserStatus;
import com.hmall.user.mapper.UserMapper;
import com.hmall.user.service.IUserService;
import com.hmall.user.util.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVO login(LoginFormDTO loginDTO) {
        // 1.数据校验
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        log.info("接收到登录请求，用户名: {}", username);
        
        // 2.根据用户名或手机号查询
        User user = lambdaQuery().eq(User::getUsername, username).one();
        log.info("查询到用户信息: {}", user);
        Assert.notNull(user, "用户名错误");
        
        // 3.校验是否禁用
//        if (user.getStatus() == UserStatus.FROZEN) {
//            throw new ForbiddenException("用户被冻结");
//        }
        
        // 4.校验密码 - 暂时直接比较密码，不使用BCrypt
        if (!password.equals(user.getPassword())) {
            log.warn("密码不匹配，用户名: {}", username);
            throw new BadRequestException("用户名或密码错误");
        }
        log.info("密码验证通过");
        
        // 5.生成TOKEN
        String token = jwtTool.createToken(user.getId(), jwtProperties.getTokenTTL());
        log.info("生成token: {}", token);
        
        // 6.封装VO返回
        UserLoginVO vo = new UserLoginVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setToken(token);
        log.info("返回登录信息: {}", vo);
        return vo;
    }

    @Override
    public void deductMoney(String pw, Integer totalFee) {
        log.info("开始扣款");
        // 1.校验密码
        User user = getById(UserContext.getUser());
        if(user == null || !passwordEncoder.matches(pw, user.getPassword())){
            // 密码错误
            throw new BizIllegalException("用户密码错误");
        }

        // 2.尝试扣款
        try {
            baseMapper.updateMoney(UserContext.getUser(), totalFee);
        } catch (Exception e) {
            throw new RuntimeException("扣款失败，可能是余额不足！", e);
        }
        log.info("扣款成功");
    }
}
