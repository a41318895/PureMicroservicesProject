package com.aki.companyms.Company.service;

import com.aki.companyms.Company.client.ReviewClient;
import com.aki.companyms.Company.dto.ReviewMessage;
import com.aki.companyms.Company.entity.Company;
import com.aki.companyms.Company.entity.response.ResponseResult;
import com.aki.companyms.Company.exception.customedException.ServiceException;
import com.aki.companyms.Company.external.AverageRatingVo;
import com.aki.companyms.Company.httpCode.HttpCodeEnum;
import com.aki.companyms.Company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    @Override
    public ResponseResult<List<Company>> getAllCompanies() {

        List<Company> companies = companyRepository.findAll() ;
        log.info("Got list of all companies successfully !") ;

        return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, companies) ;
    }

    @Override
    public ResponseResult<Company> getCompanyById(Long id) {

        Company foundCompany = isCompanyExist(id);
        log.info("Got the corresponding company [ id = {} ] successfully !", foundCompany.getId()) ;

        return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, foundCompany) ;
    }

    @Override
    public ResponseResult<Object> createCompany(Company company) {

        companyRepository.save(company) ;
        log.info("Created a new company successfully !") ;

        return ResponseResult.successWithCode(HttpCodeEnum.CREATED) ;
    }

    @Override
    public ResponseResult<Object> updateCompany(Company company, Long id){

        Company foundCompany = isCompanyExist(id);

        companyRepository.save(foundCompany) ;
        log.info("Updated the information of company [ id = {} ] successfully !", foundCompany.getId()) ;

        return ResponseResult.successWithCodeAndMsg(HttpCodeEnum.ACCEPTED) ;
    }

    @Override
    public ResponseResult<Object> deleteCompany(Long id){

        Company foundCompany = isCompanyExist(id);

        companyRepository.delete(foundCompany) ;
        log.info("Deleted a company [ id = {} ] successfully !", foundCompany.getId()) ;

        return ResponseResult.successWithCodeAndMsg(HttpCodeEnum.NO_CONTENT) ;
    }

    @Override
    public ResponseResult<Object> updateCompanyRating(ReviewMessage reviewMessage) {

        Company foundCompany = isCompanyExist(reviewMessage.getCompanyId());

        ResponseResult<AverageRatingVo> response = reviewClient.getAverageRating(reviewMessage.getCompanyId());
        AverageRatingVo averageRatingVo = response.getData();

        foundCompany.setRating(averageRatingVo.getAverageRatingScore());
        companyRepository.save(foundCompany) ;

        log.info("Updated the rating of company [ id = {} ] successfully !", foundCompany.getId()) ;

        return ResponseResult.successWithCodeAndMsg(HttpCodeEnum.UPDATE_COMPANY_RATING_SUCCESS) ;
    }

    // Common Checking Method
    private Company isCompanyExist(Long companyId) {

        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ServiceException(HttpCodeEnum.COMPANY_NOT_FOUND));
    }
}
