package com.aki.reviewms.review.exception.globalHandling;

import com.aki.reviewms.review.exception.customedException.ServiceException;
import com.aki.reviewms.review.httpCode.HttpCodeEnum;
import com.aki.reviewms.review.entity.response.ResponseResult ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Object> handleServiceException(ServiceException e){

        log.error("Review Service Internal Business Exception", e);

        return ResponseResult.errorWithCodeAndMsg(e.getCode(), e.getMessage()) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> handleException(Exception e){

        log.error("Review Service Other Exception", e);

        return ResponseResult.errorWithCodeAndMsg(HttpCodeEnum.INTERNAL_SERVER_ERROR) ;
    }
}
