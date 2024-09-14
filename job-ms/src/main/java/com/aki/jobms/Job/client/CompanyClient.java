package com.aki.jobms.Job.client;

import com.aki.jobms.Job.entity.response.ResponseResult;
import com.aki.jobms.Job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service", url = "${company-service.url}")
public interface CompanyClient {

    @GetMapping("/api/company/{companyId}")
    ResponseResult<Company> getCompany(@PathVariable("companyId") Long companyId);
}
