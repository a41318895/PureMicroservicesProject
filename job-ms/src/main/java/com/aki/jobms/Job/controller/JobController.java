package com.aki.jobms.Job.controller;

import com.aki.jobms.Job.entity.Job;
import com.aki.jobms.Job.entity.response.ResponseResult;
import com.aki.jobms.Job.service.JobServiceImpl;
import com.aki.jobms.Job.vo.JobVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobServiceImpl jobService ;

    @GetMapping(value = "/{id}")
    public ResponseResult<JobVo> getJobById(@PathVariable("id") Long id){

        return jobService.getJobById(id);
    }

    @GetMapping
    public CompletableFuture<ResponseResult<List<JobVo>>> getAllJobs() {

        return jobService.getAllJobs() ;
    }

    @PostMapping
    public ResponseResult<Object> createNewJob(@Valid @RequestBody Job job) {

        return jobService.createNewJob(job) ;
    }

    @PutMapping("/{id}")
    public ResponseResult<Object> updateJob(@Valid @RequestBody Job job, @PathVariable("id") Long id) {

        return jobService.updateJob(job, id);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteJob(@PathVariable("id") Long id) {

        return jobService.deleteJobById(id);
    }
}
