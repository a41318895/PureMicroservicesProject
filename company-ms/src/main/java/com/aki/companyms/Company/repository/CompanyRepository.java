package com.aki.companyms.Company.repository;

import com.aki.companyms.Company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
