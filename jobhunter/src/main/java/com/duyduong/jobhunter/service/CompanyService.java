package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;
import com.duyduong.jobhunter.domain.user.Company;
import com.duyduong.jobhunter.domain.dto.response.ResultPaginationDTO;
import com.duyduong.jobhunter.repository.CompanyRespository;
import com.duyduong.jobhunter.util.error.JobHunterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRespository companyRespository;

    public CompanyService(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    public Company handleCreateCompany(Company company) {
        return this.companyRespository.save(company);
    }

    public ResultPaginationDTO handleGetAllCompany(Specification specification, Pageable pageable) {
        Page<Company> companyPage = this.companyRespository.findAll(specification, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        ResultPaginationDTO.MetaDTO meta = new ResultPaginationDTO.MetaDTO();

        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());

        meta.setPages(companyPage.getTotalPages());
        meta.setTotal(companyPage.getTotalElements());

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(companyPage.getContent());

        return resultPaginationDTO;
    }

    public Company handleGetCompanyById(Long id) {
        Company company = this.companyRespository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id))
        );
        return company;
    }

    public Company handleUpdateCompany(Company company) {

        Company newCompany = this.handleGetCompanyById(company.getId());

        newCompany.setName(company.getName());
        newCompany.setLogo(company.getLogo());
        newCompany.setAddress(company.getAddress());
        newCompany.setDescription(company.getDescription());
        this.companyRespository.save(newCompany);
        return newCompany;
    }

    public void handleDeleteCompany(Long id) {
        Company company = companyRespository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id))
        );
        this.companyRespository.deleteById(id);
    }
}
