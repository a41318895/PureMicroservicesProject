package com.aki.companyms.Company.exception.customedException;

import com.aki.companyms.Company.httpCode.HttpCodeEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final int code ;

    public ServiceException(HttpCodeEnum enums) {
        super(enums.getMessage());
        this.code = enums.getCode();
    }


}
