package com.aki.reviewms.review.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReviewMessage {

    private Long id ;
    private String title ;
    private String content ;
    private Double rating ;

    private Long companyId ;
}
