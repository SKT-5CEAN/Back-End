package com.skt5cean.cheerup.company.service;

import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.company.domain.Step;
import com.skt5cean.cheerup.company.dto.CompanyDetailDto;
import com.skt5cean.cheerup.company.repository.CompanyRepository;
import com.skt5cean.cheerup.user.domain.User;
import com.skt5cean.cheerup.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Company addCompany(String companyName, List<Step> steps, Long userId) {
        // userId로 User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 회사명 중복 체크
        if (companyRepository.existsByCompanyName(companyName)) {
            throw new IllegalArgumentException("이미 존재하는 회사 이름입니다: " + companyName);
        }

        // Company 생성 및 저장
        Company company = Company.createCompany(companyName, steps, user);
        return companyRepository.save(company);
    }

    // 유저가 지원한 회사 목록 조회
    @Transactional(readOnly = true)
    public List<Company> getCompaniesByUserId(Long userId) {
        // userId로 User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 해당 유저가 지원한 회사 목록 반환
        return companyRepository.findByUser(user);
    }

    //유저가 지원한 회사의 4가지 정보 조회
    @Transactional(readOnly = true)
    public CompanyDetailDto getCompanyDetail(Long userId, Long companyId) {
        // userId로 User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // companyId로 Company 조회
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        // 유저가 해당 회사에 지원했는지 확인
        if (!company.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 사용자는 이 회사에 지원하지 않았습니다.");
        }

        // CompanyDetailDto 반환
        return new CompanyDetailDto(
                company.getTalent(),
                company.getProduct(),
                company.getNews(),
                company.getOther()
        );
    }

    // 회사 정보 수정
    @Transactional
    public Company updateCompany(Long userId, Long companyId, String talent, String product, String news, String other) {
        // userId로 User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // companyId로 Company 조회
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        // 유저가 해당 회사의 소유자인지 확인
        if (!company.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 회사의 정보를 수정할 권한이 없습니다.");
        }

        // 회사 정보 수정
        company.updateCompanyInfo(talent, product, news, other);
        return companyRepository.save(company);
    }
}
