package com.aki.jobms.Job.exception.customedException;

import com.aki.jobms.Job.httpCode.HttpCodeEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final int code ;

    public ServiceException(HttpCodeEnum enums) {
        super(enums.getMessage());
        this.code = enums.getCode();
    }


}
