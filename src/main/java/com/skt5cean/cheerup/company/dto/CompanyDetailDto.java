package com.skt5cean.cheerup.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyDetailDto {
    private String talent;  // 인재상
    private String product; // 기업 제품
    private String news;    // 최신 뉴스
    private String other;   // 기타
}
