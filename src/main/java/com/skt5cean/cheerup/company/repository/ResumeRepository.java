package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByIdAndCompanyId(Long resumeId, Long companyId);
}
