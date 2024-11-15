package com.skt5cean.cheerup.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StepDto {
    private String stepName;  // 전형명
    private Boolean isFailed; // 전형 탈락 여부
}
