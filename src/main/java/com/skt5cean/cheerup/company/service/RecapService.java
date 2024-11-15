package com.skt5cean.cheerup.company.service;

import com.skt5cean.cheerup.company.domain.Recap;
import com.skt5cean.cheerup.company.domain.Step;
import com.skt5cean.cheerup.company.dto.RecapCreateDto;
import com.skt5cean.cheerup.company.repository.RecapRepository;
import com.skt5cean.cheerup.company.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecapService {

    private final RecapRepository recapRepository;
    private final StepRepository stepRepository;

    @Transactional
    public Recap createRecap(Long stepId, RecapCreateDto recapCreateDto) {
        // Step 조회
        Step step = stepRepository.findById(stepId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 전형입니다."));

        // Recap 생성
        Recap recap = Recap.createRecap(step, recapCreateDto.getTitle(), recapCreateDto.getContents());

        // Recap 저장
        return recapRepository.save(recap);
    }

    @Transactional(readOnly = true)
    public List<Recap> getRecapsByStepId(Long stepId) {
        // 특정 전형의 Recap 목록 조회
        return recapRepository.findByStepId(stepId);
    }
}
