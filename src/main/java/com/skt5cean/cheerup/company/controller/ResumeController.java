package com.skt5cean.cheerup.company.controller;

import com.skt5cean.cheerup.company.domain.Resume;
import com.skt5cean.cheerup.company.dto.ResumeCreateDto;
import com.skt5cean.cheerup.company.dto.ResumeDetailDto;
import com.skt5cean.cheerup.company.service.ResumeService;
import com.skt5cean.cheerup.util.api.ApiResponse;
import com.skt5cean.cheerup.util.api.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @Operation(summary = "이력서 작성")
    @PostMapping("/create/{companyId}")
    public ApiResponse<Resume> createResume(
            @PathVariable Long companyId,
            @RequestBody ResumeCreateDto resumeCreateDto
    ) {
        Resume resume = resumeService.createResume(companyId, resumeCreateDto);
        return ApiResponse.success(resume, ResponseCode.RESUME_CREATE_SUCCESS.getMessage());
    }

    @Operation(summary = "특정 회사의 이력서 조회")
    @GetMapping("/{companyId}/{resumeId}")
    public ApiResponse<ResumeDetailDto> getResumeDetails(
            @PathVariable Long companyId,
            @PathVariable Long resumeId
    ) {
        ResumeDetailDto resumeDetail = resumeService.getResumeDetails(companyId, resumeId);
        return ApiResponse.success(resumeDetail, ResponseCode.RESUME_DETAIL_FETCH_SUCCESS.getMessage());
    }
}
