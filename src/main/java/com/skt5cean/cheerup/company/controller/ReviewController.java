package com.skt5cean.cheerup.company.controller;

import com.skt5cean.cheerup.company.domain.Review;
import com.skt5cean.cheerup.company.dto.ReviewCreateDto;
import com.skt5cean.cheerup.company.dto.ReviewDto;
import com.skt5cean.cheerup.company.service.ReviewService;
import com.skt5cean.cheerup.util.api.ApiResponse;
import com.skt5cean.cheerup.util.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰(회고) 작성")
    @PostMapping("/create/{stepId}")
    public ApiResponse<Review> createReview(
            @PathVariable Long stepId,
            @RequestBody ReviewCreateDto reviewCreateDto
    ) {
        Review review = reviewService.createReview(stepId, reviewCreateDto);
        return ApiResponse.success(review, ResponseCode.REVIEW_CREATE_SUCCESS.getMessage());
    }

    @Operation(summary = "특정 회사의 리뷰 조회")
    @GetMapping("/company/{companyId}")
    public ApiResponse<List<ReviewDto>> getReviewsByCompanyId(@PathVariable Long companyId) {
        // 특정 회사의 리뷰 조회
        List<ReviewDto> reviews = reviewService.getReviewsByCompanyId(companyId);
        return ApiResponse.success(reviews, ResponseCode.REVIEW_LIST_FETCH_SUCCESS.getMessage());
    }
}
