package com.aki.reviewms.review.entity.response;

import com.aki.reviewms.review.httpCode.HttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    private int code ;
    private String message ;
    private T data ;

    private ResponseResult() {}

    public static <T> ResponseResult<T> successWithData(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setCode(HttpCodeEnum.SUCCESS.getCode());
        responseResult.setMessage(HttpCodeEnum.SUCCESS.getMessage());

        if(data != null) {
            responseResult.setData(data);
        }

        return responseResult ;
    }

    public ResponseResult<T> success(int code) {
        this.code = code ;

        return this ;
    }

    public static <T> ResponseResult<T> successWithCode(HttpCodeEnum enums) {
        ResponseResult<T> responseResult = new ResponseResult<>() ;

        return responseResult.success(enums.getCode()) ;
    }

    public ResponseResult<T> success(int code, String message) {
        this.code = code ;
        this.message = message;

        return this ;
    }

    public static <T> ResponseResult<T> successWithCodeAndMsg(HttpCodeEnum enums) {
        ResponseResult<T> responseResult = new ResponseResult<>() ;

        return responseResult.success(enums.getCode(), enums.getMessage()) ;
    }

    public ResponseResult<T> success(int code, String message, T data) {
        this.code = code ;
        this.message = message;
        this.data = data ;

        return this ;
    }

    public static <T> ResponseResult<T> successWithAllStatus(HttpCodeEnum enums, T data) {
        ResponseResult<T> responseResult = new ResponseResult<>() ;

        return responseResult.success(enums.getCode(), enums.getMessage(), data) ;
    }

    public ResponseResult<T> error(int code) {
        this.code = code ;

        return this ;
    }

    public static <T> ResponseResult<T> errorWithCode(HttpCodeEnum enums) {
        ResponseResult<T> responseResult = new ResponseResult<>() ;

        return responseResult.error(enums.getCode()) ;
    }

    public ResponseResult<T> error(int code, String message) {
        this.code = code ;
        this.message = message;

        return this ;
    }

    public static <T> ResponseResult<T> errorWithCodeAndMsg(HttpCodeEnum enums) {
        ResponseResult<T> responseResult = new ResponseResult<>() ;

        return responseResult.error(enums.getCode(), enums.getMessage()) ;
    }

    public static <T> ResponseResult<T> errorWithCodeAndMsg(int code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>() ;

        return responseResult.error(code, message) ;
    }
}
