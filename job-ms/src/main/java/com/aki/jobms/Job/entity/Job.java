package com.aki.jobms.Job.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String jobTitle ;
    private String description ;
    private BigDecimal minSalary ;
    private BigDecimal maxSalary ;
    private String location ;

    private Long companyId ;

}
