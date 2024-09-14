package com.aki.companyms.Company.controller;

import com.aki.companyms.Company.entity.Company;
import com.aki.companyms.Company.entity.response.ResponseResult;
import com.aki.companyms.Company.service.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    @GetMapping
    public ResponseResult<List<Company>> getAllCompanies() {

        return companyService.getAllCompanies() ;
    }

    @GetMapping("/{id}")
    public ResponseResult<Company> getCompanyById(@PathVariable("id") Long id) {

        return companyService.getCompanyById(id) ;
    }

    @PostMapping
    public ResponseResult<Object> createCompany(@RequestBody Company companyRequest) {

        return companyService.createCompany(companyRequest);
    }

    @PutMapping("/{id}")
    public ResponseResult<Object> updateCompany(@RequestBody Company companyRequest,
                                                @PathVariable("id") Long id) {

        return companyService.updateCompany(companyRequest, id);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteCompany(@PathVariable("id") Long id) {

        return companyService.deleteCompany(id);
    }
}
