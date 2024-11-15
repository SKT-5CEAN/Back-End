package com.skt5cean.cheerup.company.controller;

import com.skt5cean.cheerup.company.domain.Step;
import com.skt5cean.cheerup.company.dto.StepDto;
import com.skt5cean.cheerup.company.dto.StepUpdateDto;
import com.skt5cean.cheerup.company.service.StepService;
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
@RequestMapping("/api/steps")
public class StepController {

    private final StepService stepService;

    @Operation(summary = "전형 합불 상태 업데이트")
    @PutMapping("/{stepId}/update-status")
    public ApiResponse<Step> updateStepStatus(
            @PathVariable Long stepId,
            @RequestBody StepUpdateDto stepUpdateDto
    ) {
        Step updatedStep = stepService.updateStepStatus(stepId, stepUpdateDto);
        return ApiResponse.success(updatedStep, ResponseCode.STEP_STATUS_UPDATE_SUCCESS.getMessage());
    }

    @Operation(summary = "특정 회사의 전형 일정 조회")
    @GetMapping("/{companyId}/steps")
    public ApiResponse<List<StepDto>> getCompanySteps(@PathVariable Long companyId) {
        // 특정 회사의 전형 일정 조회
        List<StepDto> steps = stepService.getStepsByCompanyId(companyId);
        return ApiResponse.success(steps, ResponseCode.STEP_LIST_FETCH_SUCCESS.getMessage());
    }
}
