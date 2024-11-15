package com.skt5cean.cheerup.company.service;

import com.skt5cean.cheerup.company.domain.Step;
import com.skt5cean.cheerup.company.dto.StepDto;
import com.skt5cean.cheerup.company.dto.StepUpdateDto;
import com.skt5cean.cheerup.company.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;

    @Transactional
    public Step updateStepStatus(Long stepId, StepUpdateDto stepUpdateDto) {
        // Step ID로 전형 조회
        Step step = stepRepository.findById(stepId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 전형입니다."));

        // isFailed 값 업데이트
        step.updateStatus(stepUpdateDto.getIsFailed());

        // 저장 후 반환
        return stepRepository.save(step);
    }

    @Transactional(readOnly = true)
    public List<StepDto> getStepsByCompanyId(Long companyId) {
        // 회사 ID를 기준으로 전형 목록 조회
        List<Step> steps = stepRepository.findByCompanyId(companyId);

        // Step -> StepDto 변환
        return steps.stream()
                .map(step -> new StepDto(step.getStepName(), step.getIsFailed()))
                .collect(Collectors.toList());
    }
}
