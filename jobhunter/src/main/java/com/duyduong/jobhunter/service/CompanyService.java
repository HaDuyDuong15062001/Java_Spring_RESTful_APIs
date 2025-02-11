package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;
import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.repository.CompanyRespository;
import com.duyduong.jobhunter.util.error.JobHunterException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRespository companyRespository;

    public CompanyService(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    public Company handleCreateCompany(Company company) {
        return this.companyRespository.save(company);
    }

    public List<Company> handleGetAllCompany() {
        return this.companyRespository.findAll();
    }

    public Company handleGetCompanyById(Long id) {
        Optional<Company> company = this.companyRespository.findById(id);
        if(company.isPresent())
            return company.get();
        return null;
    }

    public Company handleUpdateCompany(Company company) {
        Company curCom = this.handleGetCompanyById(company.getId());
        if (curCom != null) {
            curCom.setName(company.getName());
            curCom.setLogo(company.getLogo());
            curCom.setAddress(company.getAddress());
            curCom.setDescription(company.getDescription());
//            curCom.setCreatedBy(company.getCreatedBy());
//            curCom.setCreatedAt(company.getCreatedAt());
            this.companyRespository.save(curCom);
        }
        return curCom;
    }

    public void handleDeleteCompany(Long id) {
        Optional<Company> company = companyRespository.findById(id);
        if (company.isEmpty()) {
            throw new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id));
        }
        this.companyRespository.deleteById(id);
    }
}
