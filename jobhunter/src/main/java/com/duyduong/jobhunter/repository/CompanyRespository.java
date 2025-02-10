package com.duyduong.jobhunter.repository;

import com.duyduong.jobhunter.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRespository extends JpaRepository<Company, Long> {
}
