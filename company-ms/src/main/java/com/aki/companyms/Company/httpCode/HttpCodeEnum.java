package com.aki.companyms.Company.httpCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpCodeEnum {

    SUCCESS(200, "操作成功"),
    CREATED(201, "新公司資訊創建成功"),
    ACCEPTED(202, "公司資訊更新成功"),
    NO_CONTENT(204, "成功刪除公司"),
    INTERNAL_SERVER_ERROR(500, "Company MS 系統內部錯誤"),

    COMPANY_NOT_FOUND(601, "找尋不到該公司資訊"),
    UPDATE_COMPANY_RATING_SUCCESS(602, "更新公司評分成功") ;

    final int code ;
    final String message ;
}
