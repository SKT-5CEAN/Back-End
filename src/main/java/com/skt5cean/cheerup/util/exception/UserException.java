package com.skt5cean.cheerup.util.exception;

import com.skt5cean.cheerup.util.api.ResponseCode;

public class UserException extends BaseException {

    public UserException(ResponseCode responseCode) {
        super(responseCode);
    }
}
