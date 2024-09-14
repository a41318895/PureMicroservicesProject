package com.aki.jobms.Job.external;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Review {

    private Long id ;
    private String title ;
    private String content ;
    private BigDecimal rating ;
}
