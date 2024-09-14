package com.aki.reviewms.review.exception.customedException;

import com.aki.reviewms.review.httpCode.HttpCodeEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final int code ;

    public ServiceException(HttpCodeEnum enums) {
        super(enums.getMessage());
        this.code = enums.getCode();
    }


}
