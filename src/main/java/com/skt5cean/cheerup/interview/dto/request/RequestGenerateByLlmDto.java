package com.skt5cean.cheerup.interview.dto.request;


import com.skt5cean.cheerup.company.domain.Company;
import com.skt5cean.cheerup.company.domain.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestGenerateByLlmDto {
    private List<String> contexts;

    public static RequestGenerateByLlmDto from(Company company) {
        List<Resume> resumes = company.getResumes();
        List<String> formattedList = new ArrayList<>();
        for (Resume resume : resumes) {
            formattedList.add(String.format("%s: %s", resume.getTitle(), resume.getContents()));
        }
        return new RequestGenerateByLlmDto(formattedList);
    }

}

