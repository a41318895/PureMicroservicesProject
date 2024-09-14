package com.aki.companyms.Company.exception.globalHandling;

import com.aki.companyms.Company.entity.response.ResponseResult ;
import com.aki.companyms.Company.exception.customedException.ServiceException;
import com.aki.companyms.Company.httpCode.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Object> handleServiceException(ServiceException e){

        log.error("Company Service Internal Business Exception", e);

        return ResponseResult.errorWithCodeAndMsg(e.getCode(), e.getMessage()) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> handleException(Exception e){

        log.error("Company Service Other Exception", e);

        return ResponseResult.errorWithCodeAndMsg(HttpCodeEnum.INTERNAL_SERVER_ERROR) ;
    }
}
