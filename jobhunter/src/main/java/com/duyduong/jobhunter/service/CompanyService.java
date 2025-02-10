package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.repository.CompanyRespository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRespository companyRespository;

    public CompanyService(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    public Company handleCreateCompany(Company company) {
        return this.companyRespository.save(company);
    }
}
