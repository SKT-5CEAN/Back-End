package com.skt5cean.cheerup.interview.controller;

import com.skt5cean.cheerup.auth.component.JwtTokenProvider;
import com.skt5cean.cheerup.company.domain.Interview;
import com.skt5cean.cheerup.interview.dto.request.RequestGenerateByLlmDto;
import com.skt5cean.cheerup.interview.dto.request.RequestGenerateInterviewQuestionsDto;
import com.skt5cean.cheerup.interview.service.InterviewService;
import com.skt5cean.cheerup.util.api.ApiResponse;
import com.skt5cean.cheerup.util.api.ResponseCode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview")
public class InterviewController {
    private final InterviewService interviewService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "면접 질문 생성 요청")
    @PostMapping("/generate")
    public ApiResponse<Void> generateInterview(@RequestHeader String accessToken, @RequestBody RequestGenerateInterviewQuestionsDto requestgenerateInterviewQuestionsDto) throws IOException {
        // Long userId = Long.parseLong((jwtTokenProvider.getUserPk(accessToken)));
        Long companyId = requestgenerateInterviewQuestionsDto.getCompanyId();
        if (companyId == null) {
            log.debug(requestgenerateInterviewQuestionsDto.toString());
            throw new IllegalArgumentException("companyId cannot be null");
        }

        interviewService.generateInterview(companyId);
        return ApiResponse.success(null, ResponseCode.INTERVIEW_GENERATION_SUCCESS.getMessage());
    }
}
