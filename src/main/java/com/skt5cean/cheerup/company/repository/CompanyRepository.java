package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyById(long id);
}
