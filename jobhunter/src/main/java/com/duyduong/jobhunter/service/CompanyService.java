package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;
import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.domain.dto.MetaDTO;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.repository.CompanyRespository;
import com.duyduong.jobhunter.util.error.JobHunterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public ResultPaginationDTO handleGetAllCompany(Pageable pageable) {
        Page<Company>  companyPage = this.companyRespository.findAll(pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        MetaDTO meta = new MetaDTO();

        meta.setPage(companyPage.getNumber());
        meta.setPageSize(companyPage.getSize());

        meta.setPages(companyPage.getTotalPages());
        meta.setTotal(companyPage.getTotalElements());

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(companyPage.getContent());
        return resultPaginationDTO;
    }

    public Company handleGetCompanyById(Long id) {
        Company company = companyRespository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id))
        );
        return company;
    }

    public Company handleUpdateCompany(Company company) {
        Company curCom = this.handleGetCompanyById(company.getId());
        if (curCom != null) {
            curCom.setName(company.getName());
            curCom.setLogo(company.getLogo());
            curCom.setAddress(company.getAddress());
            curCom.setDescription(company.getDescription());
            this.companyRespository.save(curCom);
        }
        return curCom;
    }

    public void handleDeleteCompany(Long id) {
        Company company = companyRespository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id))
        );
        this.companyRespository.deleteById(id);
    }
}
