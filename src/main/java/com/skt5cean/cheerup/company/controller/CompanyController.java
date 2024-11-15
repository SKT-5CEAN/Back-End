package com.skt5cean.cheerup.company.controller;

import com.skt5cean.cheerup.auth.component.JwtTokenProvider;
import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.company.domain.Step;
import com.skt5cean.cheerup.company.dto.CompanyDetailDto;
import com.skt5cean.cheerup.company.service.CompanyService;
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
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "지원 회사 추가")
    @PostMapping("/add")
    public ApiResponse<Company> addCompany(
            @RequestHeader String accessToken,
            @RequestParam String companyName,
            @RequestBody List<Step> steps
    ) {
        // JWT를 통해 사용자 ID 추출
        Long userId = Long.parseLong(jwtTokenProvider.getUserPk(accessToken));

        // Company 생성 및 저장
        Company company = companyService.addCompany(companyName, steps, userId);
        return ApiResponse.success(company, ResponseCode.COMPANY_ADDED_SUCCESS.getMessage());
    }

    @Operation(summary = "유저가 지원한 회사 목록 조회")
    @GetMapping("/my-applied")
    public ApiResponse<List<Company>> getUserAppliedCompanies(
            @RequestHeader String accessToken
    ) {
        // JWT에서 userId 추출
        Long userId = Long.parseLong(jwtTokenProvider.getUserPk(accessToken));

        // 유저가 지원한 회사 목록 조회
        List<Company> companies = companyService.getCompaniesByUserId(userId);
        return ApiResponse.success(companies, ResponseCode.USER_APPLIED_COMPANIES_FETCH_SUCCESS.getMessage());
    }

    @Operation(summary = "특정 회사 상세 정보 조회")
    @GetMapping("/{companyId}/details")
    public ApiResponse<CompanyDetailDto> getCompanyDetail(
            @RequestHeader String accessToken,
            @PathVariable Long companyId
    ) {
        // JWT에서 userId 추출
        Long userId = Long.parseLong(jwtTokenProvider.getUserPk(accessToken));

        // 특정 회사 상세 정보 조회
        CompanyDetailDto companyDetail = companyService.getCompanyDetail(userId, companyId);
        return ApiResponse.success(companyDetail, ResponseCode.COMPANY_DETAIL_FETCH_SUCCESS.getMessage());
    }

    @Operation(summary = "회사 정보 수정")
    @PutMapping("/{companyId}/update")
    public ApiResponse<Company> updateCompany(
            @RequestHeader String accessToken,
            @PathVariable Long companyId,
            @RequestParam String talent,
            @RequestParam String product,
            @RequestParam String news,
            @RequestParam String other
    ) {
        // JWT에서 userId 추출
        Long userId = Long.parseLong(jwtTokenProvider.getUserPk(accessToken));

        // 회사 정보 수정
        Company updatedCompany = companyService.updateCompany(userId, companyId, talent, product, news, other);
        return ApiResponse.success(updatedCompany, ResponseCode.COMPANY_UPDATE_SUCCESS.getMessage());
    }
}
