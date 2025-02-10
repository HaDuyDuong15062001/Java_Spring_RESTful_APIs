package com.duyduong.jobhunter.controller;


import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.domain.dto.TestDTO;
import com.duyduong.jobhunter.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createNewCompanyPostMethod (@Valid @RequestBody Company companyPost) {
        Company newCompany = this.companyService.handleCreateCompany(companyPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }
}
