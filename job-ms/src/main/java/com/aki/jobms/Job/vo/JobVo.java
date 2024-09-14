package com.aki.jobms.Job.vo;

import com.aki.jobms.Job.external.Company;
import com.aki.jobms.Job.external.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobVo {

    private Long id ;
    private String jobTitle ;
    private String description ;
    private BigDecimal minSalary ;
    private BigDecimal maxSalary ;
    private String location ;

    private Company company ;
    private List<Review> reviews ;

    private String feedBackMessage ;
}
