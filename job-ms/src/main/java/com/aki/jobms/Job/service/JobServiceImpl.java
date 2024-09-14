package com.aki.jobms.Job.service;

import com.aki.jobms.Job.client.CompanyClient;
import com.aki.jobms.Job.client.ReviewClient;
import com.aki.jobms.Job.external.Review;
import com.aki.jobms.Job.httpCode.HttpCodeEnum;
import com.aki.jobms.Job.entity.Job;
import com.aki.jobms.Job.entity.response.ResponseResult;
import com.aki.jobms.Job.exception.customedException.ServiceException;
import com.aki.jobms.Job.external.Company;
import com.aki.jobms.Job.mapper.JobMapper;
import com.aki.jobms.Job.repository.JobRepository;
import com.aki.jobms.Job.vo.JobVo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;
    //private final RestTemplate restTemplate;
    private final CompanyClient companyClient ;
    private final ReviewClient reviewClient ;

    private int retryCount = 1 ;

    @Override
    //@Retry(name = "jobBreaker", fallbackMethod = "")
    public ResponseResult<JobVo> getJobById(Long id) {

        Job foundJob = isJobExist(id);

        log.info("Got the corresponding job [ id = {} ] successfully !", foundJob.getId()) ;

        JobVo jobVo = convertToJobVo(foundJob);

        return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, jobVo) ;
    }

    @Override
    @TimeLimiter(name = "jobBreaker", fallbackMethod = "jobBreakerFallback")
    @CircuitBreaker(name = "jobBreaker", fallbackMethod = "jobBreakerFallback")
    @RateLimiter(name = "jobBreaker", fallbackMethod = "jobBreakerFallback")
    public CompletableFuture<ResponseResult<List<JobVo>>> getAllJobs() {

        return CompletableFuture.supplyAsync(() -> {

            System.out.println("Get All Jobs Attempts : " + retryCount++);

            List<Job> jobs = jobRepository.findAll() ;
            List<JobVo> jobVos =
                    jobs.stream().map(this::convertToJobVo).collect(Collectors.toList()) ;

            log.info("Got list of all jobs successfully !") ;

            return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, jobVos) ;
        }) ;
    }

    public CompletableFuture<ResponseResult<List<JobVo>>> jobBreakerFallback(Throwable e) {

        List<JobVo> fallbackJobVoList = new ArrayList<>();

        JobVo fallbackJobVo =
                JobVo.builder().feedBackMessage("Fallback method triggered ! " + e.getMessage()).build();

        fallbackJobVoList.add(fallbackJobVo);

        return CompletableFuture.completedFuture(
            ResponseResult.errorWithCodeAndMsg(HttpCodeEnum.INTERNAL_SERVER_ERROR, fallbackJobVoList)) ;
    }

    private JobVo convertToJobVo(Job job) {

        // Get corresponding company via companyId in transferred job
        /*
        Company company =
                restTemplate.getForObject("http://company-service:8081/api/company/" + job.getCompanyId(),
                        Company.class);

        ResponseEntity<List<Review>> exchange = restTemplate.exchange("http://review-service:8083/api/review?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });
        List<Review> reviews = exchange.getBody();

         */


        Company company = companyClient.getCompany(job.getCompanyId()).getData() ;

        List<Review> reviews = reviewClient.getAllReviews(job.getCompanyId()).getData() ;

        return JobMapper.mapToJobWithCompanyVo(job, company, reviews) ;
    }

    @Override
    public ResponseResult<Object> createNewJob(Job job) {

        jobRepository.save(job) ;

        log.info("Created a new job successfully !") ;

        return ResponseResult.successWithCodeAndMsg(HttpCodeEnum.CREATED) ;
    }

    @Override
    public ResponseResult<Object> updateJob(Job job, Long id)  {

        Job foundJob = isJobExist(id) ;

        jobRepository.save(foundJob) ;
        log.info("Updated the information of job [ id = {} ] successfully !", foundJob.getId()) ;

        return ResponseResult.successWithCodeAndMsg(HttpCodeEnum.ACCEPTED) ;
    }

    @Override
    public ResponseResult<Object> deleteJobById(Long id) {

        Job foundJob = isJobExist(id) ;

        jobRepository.delete(foundJob) ;
        log.info("Deleted a job [ id = {} ] successfully !", foundJob.getId()) ;

        return ResponseResult.successWithCodeAndMsg(HttpCodeEnum.NO_CONTENT) ;
    }

    private Job isJobExist(Long jobId) {

        return jobRepository.findById(jobId)
                .orElseThrow(() -> new ServiceException(HttpCodeEnum.JOB_NOT_FOUND));
    }
}
