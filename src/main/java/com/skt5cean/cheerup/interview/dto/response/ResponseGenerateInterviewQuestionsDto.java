package com.skt5cean.cheerup.interview.dto.response;


import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.company.domain.Interview;
import com.skt5cean.cheerup.company.domain.Recap;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseGenerateInterviewQuestionsDto {
    private List<Interview> interviewList;

    public static ResponseGenerateInterviewQuestionsDto from(Company company){
        return new ResponseGenerateInterviewQuestionsDto(company.getInterviews());
    }
}
