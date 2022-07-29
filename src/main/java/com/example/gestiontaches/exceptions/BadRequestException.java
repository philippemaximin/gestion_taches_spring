package com.example.gestiontaches.exceptions;

import com.example.gestiontaches.enums.ErrorCode;

public class BadRequestException extends RuntimeException {
    private ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


}
