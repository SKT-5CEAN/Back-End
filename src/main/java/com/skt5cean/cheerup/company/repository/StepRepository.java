package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findByCompanyId(Long companyId);
}
