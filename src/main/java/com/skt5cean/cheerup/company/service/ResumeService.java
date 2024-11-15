package com.skt5cean.cheerup.company.service;

import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.company.domain.Resume;
import com.skt5cean.cheerup.company.dto.ResumeCreateDto;
import com.skt5cean.cheerup.company.dto.ResumeDetailDto;
import com.skt5cean.cheerup.company.repository.CompanyRepository;
import com.skt5cean.cheerup.company.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Resume createResume(Long companyId, ResumeCreateDto resumeCreateDto) {
        // companyId로 Company 조회
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        // Resume 생성 및 저장
        Resume resume = Resume.createResume(company, resumeCreateDto.getTitle());

        resume.update(resumeCreateDto.getTitle(), resumeCreateDto.getContent());
        return resumeRepository.save(resume);
    }

    @Transactional(readOnly = true)
    public ResumeDetailDto getResumeDetails(Long companyId, Long resumeId) {
        // 회사 ID와 이력서 ID로 이력서 조회
        Resume resume = resumeRepository.findByIdAndCompanyId(resumeId, companyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회사 또는 이력서가 존재하지 않습니다."));

        // title과 content를 포함한 DTO 반환
        return new ResumeDetailDto(resume.getTitle(), resume.getContents());
    }
}
