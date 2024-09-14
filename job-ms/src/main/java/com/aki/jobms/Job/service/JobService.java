package com.aki.jobms.Job.service;

import com.aki.jobms.Job.entity.Job;
import com.aki.jobms.Job.entity.response.ResponseResult;
import com.aki.jobms.Job.vo.JobVo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JobService {

    ResponseResult<JobVo> getJobById(Long id) ;

    CompletableFuture<ResponseResult<List<JobVo>>> getAllJobs() ;

    ResponseResult<Object> createNewJob(Job job) ;

    ResponseResult<Object> updateJob(Job job, Long id) ;

    ResponseResult<Object> deleteJobById(Long id) ;
}
