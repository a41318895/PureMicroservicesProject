package com.aki.jobms.Job.exception.globalHandling;

import com.aki.jobms.Job.httpCode.HttpCodeEnum;
import com.aki.jobms.Job.entity.response.ResponseResult;
import com.aki.jobms.Job.exception.customedException.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Object> handleServiceException(ServiceException e){

        log.error("Job Service Internal Business Exception", e);

        return ResponseResult.errorWithCodeAndMsg(e.getCode(), e.getMessage()) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> handleException(Exception e){

        log.error("Job Service Other Exception", e);

        return ResponseResult.errorWithCodeAndMsg(HttpCodeEnum.INTERNAL_SERVER_ERROR) ;
    }
}
