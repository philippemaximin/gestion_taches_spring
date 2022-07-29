package com.example.gestiontaches.controller.advice;

import com.example.gestiontaches.dto.ErrorDto;
import com.example.gestiontaches.enums.ErrorCode;
import com.example.gestiontaches.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllersAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public @ResponseBody ErrorDto handleIllegalArgumentException(Exception exception) {
        exception.printStackTrace();

        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setCode(ErrorCode.USER_EXIST);

        return errorDTO;
    }
}
