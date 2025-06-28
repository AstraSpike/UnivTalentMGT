package com.hmall.team.client;

import com.hmall.team.domain.dto.TeamRequirementDTO;
import com.hmall.team.domain.dto.ApiResponse;
import com.hmall.team.domain.dto.AlgorithmResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 算法服务客户端
 */
@FeignClient(name = "algorithm-service", url = "${algorithm.service.team-recommend.url}")
public interface AlgorithmClient {
    
    /**
     * 调用算法服务进行班子推荐
     */
    @PostMapping
    ApiResponse<AlgorithmResponseDTO> recommendTeam(@RequestBody TeamRequirementDTO requirement);
} 