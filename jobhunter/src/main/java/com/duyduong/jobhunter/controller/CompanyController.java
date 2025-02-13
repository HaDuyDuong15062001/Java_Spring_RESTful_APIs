package com.duyduong.jobhunter.controller;


import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.domain.RestResponse;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.domain.dto.TestDTO;
import com.duyduong.jobhunter.service.CompanyService;
import com.duyduong.jobhunter.util.error.IdInvalidException;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ResultPaginationDTO> getAllCompany(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional
            ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body( this.companyService.handleGetAllCompany(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        Company company = this.companyService.handleGetCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(company);
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
