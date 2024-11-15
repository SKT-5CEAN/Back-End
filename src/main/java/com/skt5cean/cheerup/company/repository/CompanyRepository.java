package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // 회사명을 기준으로 조회
    boolean existsByCompanyName(String companyName);

    List<Company> findByUser(User user);
}
