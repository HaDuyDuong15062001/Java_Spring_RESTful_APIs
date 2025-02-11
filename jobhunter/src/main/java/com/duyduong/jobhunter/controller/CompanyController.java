package com.duyduong.jobhunter.controller;


import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.domain.RestResponse;
import com.duyduong.jobhunter.domain.dto.TestDTO;
import com.duyduong.jobhunter.service.CompanyService;
import com.duyduong.jobhunter.util.error.IdInvalidException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createNewCompanyPostMethod (@Valid @RequestBody Company companyPost) {
        Company newCompany = this.companyService.handleCreateCompany(companyPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        List<Company> arrCom = this.companyService.handleGetAllCompany();
        return ResponseEntity.status(HttpStatus.OK).body(arrCom);
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company c) {
        Company company = this.companyService.handleUpdateCompany(c);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable("id") Long id) {
        this.companyService.handleDeleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
