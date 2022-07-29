package com.example.gestiontaches.dto;

import com.example.gestiontaches.enums.ErrorCode;

public class ErrorDto {
    private ErrorCode code;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
