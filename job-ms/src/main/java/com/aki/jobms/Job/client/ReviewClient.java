package com.aki.jobms.Job.client;

import com.aki.jobms.Job.entity.response.ResponseResult;
import com.aki.jobms.Job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "review-service", url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/api/review")
    ResponseResult<List<Review>> getAllReviews(@RequestParam("companyId") Long companyId);

}
