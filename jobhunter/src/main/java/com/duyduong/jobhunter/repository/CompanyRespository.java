package com.duyduong.jobhunter.repository;

import com.duyduong.jobhunter.domain.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRespository extends
        JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    boolean existsById(Long Id);
}
