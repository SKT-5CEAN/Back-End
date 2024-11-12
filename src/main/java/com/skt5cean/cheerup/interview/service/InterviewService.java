package com.skt5cean.cheerup.interview.service;

import com.skt5cean.cheerup.company.domain.Interview;
import com.skt5cean.cheerup.interview.dto.request.RequestGenerateByLlmDto;
import com.skt5cean.cheerup.interview.dto.response.ResponseGenerateInterviewQuestionsDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.company.repository.CompanyRepository;
import com.skt5cean.cheerup.company.repository.InterviewRepository;
import com.skt5cean.cheerup.interview.dto.request.RequestGenerateInterviewQuestionsDto;
import com.skt5cean.cheerup.util.api.ResponseCode;
import com.skt5cean.cheerup.util.exception.BaseException;
import com.skt5cean.cheerup.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewService {
    private final WebClient webClient;

    private final InterviewRepository interviewRepository;
    private final CompanyRepository companyRepository;

    // LLM에 질문 생성 요청
    @Transactional
    public void generateInterview(Long companyId) throws IOException, BaseException {

        // TODO: 에외 처리 - 회사 혹은 회사 자소서가 없는 경우

        Company company = companyRepository.findCompanyById(companyId);
        // fastapi - mock api
        String url = "https://cean22.postman.co/workspace/5cean~55beaf5f-ffd2-4c92-8592-323800bad6df/request/34262569-58583329-ade2-408b-bbd1-09329ec0fc50?action=share&creator=34262569&ctx=documentation";

        RequestGenerateByLlmDto requestGenerateByLlmDto = RequestGenerateByLlmDto.from(company);
        // POST 요청
        ResponseGenerateInterviewQuestionsDto response = webClient.post()
                .uri(url)	// url 정의
                .bodyValue(requestGenerateByLlmDto)	// requestBody 정의
                .retrieve()	// 응답 정의 시작
                .bodyToMono(ResponseGenerateInterviewQuestionsDto.class)
                .block();

        if (response == null || response.getGeneratedQuestions().isEmpty()) {
            throw new UserException(ResponseCode.NO_INTERVIEW_QUESTIONS_GENERATED);
        }



        List<Interview> interviewQuestions = response.getGeneratedQuestions().stream()
                            .map(question -> Interview.createInterview(company, question, ""))
                            .toList();

        interviewRepository.saveAll(interviewQuestions);
        log.info("자소서 기반으로 생성된 면접 질문을 DB에 저장하였습니다.");
    }


}
