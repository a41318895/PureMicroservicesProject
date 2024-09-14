package com.aki.companyms.Company.client;

import com.aki.companyms.Company.entity.response.ResponseResult;
import com.aki.companyms.Company.external.AverageRatingVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "review-service", url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/api/review/averageRating")
    ResponseResult<AverageRatingVo> getAverageRating(@RequestParam("companyId") Long companyId);

}
