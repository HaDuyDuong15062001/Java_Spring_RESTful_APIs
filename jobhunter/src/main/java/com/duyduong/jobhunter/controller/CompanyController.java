package com.duyduong.jobhunter.controller;


import com.duyduong.jobhunter.domain.entity.Company;
import com.duyduong.jobhunter.domain.dto.response.ResultPaginationDTO;
import com.duyduong.jobhunter.service.CompanyService;
import com.duyduong.jobhunter.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiMessage("Create new company")
    @PostMapping
    public ResponseEntity<Company> createNewCompanyPostMethod(@Valid @RequestBody Company companyPost) {
        Company newCompany = this.companyService.handleCreateCompany(companyPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @ApiMessage("Get all company")
    @GetMapping
    public ResponseEntity<ResultPaginationDTO> getAllCompany(
            @Filter Specification<Company> specification, Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.companyService.handleGetAllCompany(specification, pageable));
    }

    @ApiMessage("Get company by id")
    @GetMapping("{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        Company company = this.companyService.handleGetCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @ApiMessage("Update company")
    @PutMapping
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company c) {
        Company company = this.companyService.handleUpdateCompany(c);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @ApiMessage("Delete company by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable("id") Long id) {
        this.companyService.handleDeleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
