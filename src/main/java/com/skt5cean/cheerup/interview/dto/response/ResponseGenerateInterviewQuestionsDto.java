package com.skt5cean.cheerup.interview.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseGenerateInterviewQuestionsDto {
    private List<String> generatedQuestions;

    // Constructor
    public ResponseGenerateInterviewQuestionsDto() {}

}
