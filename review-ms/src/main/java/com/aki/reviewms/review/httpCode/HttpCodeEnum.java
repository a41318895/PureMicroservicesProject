package com.aki.reviewms.review.httpCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpCodeEnum {

    SUCCESS(200, "操作成功"),
    CREATED(201, "新評論創建成功"),
    ACCEPTED(202, "評論資訊更新成功"),
    NO_CONTENT(204, "成功刪除評論"),
    INTERNAL_SERVER_ERROR(500, "Review MS 系統內部錯誤"),

    REVIEW_NOT_FOUND(601, "找尋不到該評論資訊"),
    REVIEW_CREATE_FAIL(602, "評論創建失敗");


    final int code ;
    final String message ;
}
