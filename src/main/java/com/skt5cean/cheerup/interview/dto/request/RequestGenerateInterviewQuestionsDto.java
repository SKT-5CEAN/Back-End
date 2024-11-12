package com.skt5cean.cheerup.interview.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGenerateInterviewQuestionsDto {
    private Long companyId;

    public static RequestGenerateInterviewQuestionsDto of(Long companyId) {
        return new RequestGenerateInterviewQuestionsDto(companyId);
    }

}

