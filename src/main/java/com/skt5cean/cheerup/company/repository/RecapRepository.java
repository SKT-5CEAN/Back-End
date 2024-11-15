package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Recap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecapRepository extends JpaRepository<Recap, Long> {
    // 특정 전형의 Recap 조회
    List<Recap> findByStepId(Long stepId);
}
