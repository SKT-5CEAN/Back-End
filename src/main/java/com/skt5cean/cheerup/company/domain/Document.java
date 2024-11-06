package com.skt5cean.cheerup.company.domain;

import jakarta.persistence.Column;
import lombok.Getter;

/**
 * 중복되는 질문-답변 필드를 부모 클래스 Document로 관리하고, 상속하여 처리
 */
@Getter
public class Document {

    // 질문(제목) - 반드시 존재해야 함
    @Column(nullable = false)
    protected String title;

    // 내용
    protected String contents;
}
