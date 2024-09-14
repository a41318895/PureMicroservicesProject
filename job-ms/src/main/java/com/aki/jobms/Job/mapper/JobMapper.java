package com.aki.jobms.Job.mapper;

import com.aki.jobms.Job.entity.Job;
import com.aki.jobms.Job.external.Company;
import com.aki.jobms.Job.external.Review;
import com.aki.jobms.Job.vo.JobVo;

import java.util.List;

public class JobMapper {

    public static JobVo mapToJobWithCompanyVo(Job job, Company company, List<Review> reviews) {

        return JobVo.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .description(job.getDescription())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .location(job.getLocation())
                .company(company)
                .reviews(reviews)
                .build();
    }
}
