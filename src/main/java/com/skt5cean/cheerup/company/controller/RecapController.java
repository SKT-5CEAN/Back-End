package com.skt5cean.cheerup.company.controller;

import com.skt5cean.cheerup.company.domain.Recap;
import com.skt5cean.cheerup.company.dto.RecapCreateDto;
import com.skt5cean.cheerup.company.service.RecapService;
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
@RequestMapping("/api/recaps")
public class RecapController {

    private final RecapService recapService;

    @Operation(summary = "Recap 생성")
    @PostMapping("/create/{stepId}")
    public ApiResponse<Recap> createRecap(
            @PathVariable Long stepId,
            @RequestBody RecapCreateDto recapCreateDto
    ) {
        Recap recap = recapService.createRecap(stepId, recapCreateDto);
        return ApiResponse.success(recap, ResponseCode.RECAP_CREATE_SUCCESS.getMessage());
    }

    @Operation(summary = "Recap 조회")
    @GetMapping("/step/{stepId}")
    public ApiResponse<List<Recap>> getRecapsByStepId(@PathVariable Long stepId) {
        List<Recap> recaps = recapService.getRecapsByStepId(stepId);
        return ApiResponse.success(recaps, ResponseCode.RECAP_LIST_FETCH_SUCCESS.getMessage());
    }
}
