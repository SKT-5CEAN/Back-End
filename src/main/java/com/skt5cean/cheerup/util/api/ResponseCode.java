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
    NO_INTERVIEW_QUESTIONS_GENERATED(HttpStatus.BAD_REQUEST,false, "면접 예상 질문 생성에 실패하였습니다." ),

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
    INTERVIEW_GENERATION_SUCCESS(HttpStatus.OK, true, "인터뷰 생성 성공"),

    // 201 Created
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공");




    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;
}

