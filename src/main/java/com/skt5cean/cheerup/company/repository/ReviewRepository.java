package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 회사 ID를 기준으로 모든 리뷰 조회
    List<Review> findByStep_CompanyId(Long companyId);
}
