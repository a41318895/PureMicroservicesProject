package com.aki.jobms.Job.httpCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpCodeEnum {

    SUCCESS(200, "操作成功"),
    CREATED(201, "新工作資訊創建成功"),
    ACCEPTED(202, "工作資訊更新成功"),
    NO_CONTENT(204, "成功刪除工作"),
    INTERNAL_SERVER_ERROR(500, "Job MS 系統內部錯誤"),

    JOB_NOT_FOUND(601, "找尋不到該工作資訊") ;

    final int code ;
    final String message ;
}
