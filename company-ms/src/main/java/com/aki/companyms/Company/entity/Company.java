package com.aki.companyms.Company.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String description ;

    private Double rating ;
}
