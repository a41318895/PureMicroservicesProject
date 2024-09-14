package com.aki.companyms.Company.service;

import com.aki.companyms.Company.dto.ReviewMessage;
import com.aki.companyms.Company.entity.Company;
import com.aki.companyms.Company.entity.response.ResponseResult;

import java.util.List;

public interface CompanyService {

    ResponseResult<List<Company>> getAllCompanies() ;

    ResponseResult<Company> getCompanyById(Long id);

    ResponseResult<Object> createCompany(Company companyRequest);

    ResponseResult<Object> updateCompany(Company companyRequest, Long id) ;

    ResponseResult<Object> deleteCompany(Long id) ;

    ResponseResult<Object> updateCompanyRating(ReviewMessage reviewMessage) ;
}
