package com.skt5cean.cheerup.util.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
    FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, false, "파일 업로드에 실패하였습니다."),

    // 401 Unauthorized

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "권한이 없습니다."),

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자를 찾을 수 없습니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생하였습니다."),

    // 200 OK
    USER_LOGIN_SUCCESS(HttpStatus.OK, true, "사용자 로그인 성공"),
    USER_READ_SUCCESS(HttpStatus.OK, true, "사용자 조회 성공"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, true, "사용자 수정 성공"),
    USER_DELETE_SUCCESS(HttpStatus.OK, true, "사용자 삭제 성공"),
    COMPANY_ADDED_SUCCESS(HttpStatus.OK, true, "회사 등록 성공"),
    USER_APPLIED_COMPANIES_FETCH_SUCCESS(HttpStatus.OK, true, "회사 조회 성공"),
    COMPANY_DETAIL_FETCH_SUCCESS(HttpStatus.OK, true, "회사 정보 조회 성공"),
    COMPANY_UPDATE_SUCCESS(HttpStatus.OK, true, "회사 정보 수정 성공"),
    RESUME_CREATE_SUCCESS(HttpStatus.OK, true, "이력서 작성 성공"),
    RESUME_DETAIL_FETCH_SUCCESS(HttpStatus.OK, true, "이력서 조회 성공"),
    STEP_STATUS_UPDATE_SUCCESS(HttpStatus.OK, true, "전형 상태 업데이트 성공"),
    STEP_LIST_FETCH_SUCCESS(HttpStatus.OK, true, "전형 목록 조회 성공"),
    REVIEW_CREATE_SUCCESS(HttpStatus.OK, true, "리뷰 작성 성공"),
    REVIEW_LIST_FETCH_SUCCESS(HttpStatus.OK, true, "리뷰 목록 조회 성공"),
    RECAP_CREATE_SUCCESS(HttpStatus.OK, true, "Recap 생성 성공"),
    RECAP_LIST_FETCH_SUCCESS(HttpStatus.OK, true, "Recap 조회 성공"),

    // 201 Created
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공");


    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;
}

