package com.skt5cean.cheerup.company.service;

import com.skt5cean.cheerup.company.domain.Review;
import com.skt5cean.cheerup.company.domain.Step;
import com.skt5cean.cheerup.company.dto.ReviewCreateDto;
import com.skt5cean.cheerup.company.dto.ReviewDto;
import com.skt5cean.cheerup.company.repository.ReviewRepository;
import com.skt5cean.cheerup.company.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StepRepository stepRepository;

    @Transactional
    public Review createReview(Long stepId, ReviewCreateDto reviewCreateDto) {
        // Step ID로 Step 조회
        Step step = stepRepository.findById(stepId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 전형입니다."));

        // Review 생성
        Review review = Review.createReview(step, reviewCreateDto.getContents());

        // Review 저장
        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsByCompanyId(Long companyId) {
        // 특정 회사의 모든 리뷰 조회
        List<Review> reviews = reviewRepository.findByStep_CompanyId(companyId);

        // Review -> ReviewDto 변환
        return reviews.stream()
                .map(review -> new ReviewDto(review.getContents()))
                .collect(Collectors.toList());
    }
}
