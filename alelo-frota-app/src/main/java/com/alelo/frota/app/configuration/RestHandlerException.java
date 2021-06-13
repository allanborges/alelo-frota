package com.alelo.frota.app.configuration;

import com.alelo.frota.usecase.exceptions.PlateErrorException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(value = PlateErrorException.class)
    protected ResponseEntity<ErrorMessage> plateErrorException(PlateErrorException exception, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage
                        .builder()
                        .httpErrorCode(HttpStatus.BAD_REQUEST.value())
                        .errorMsg(exception.getMessage())
                        .build());
    }

    @Builder
    @Data
    public static class ErrorMessage {
        private int httpErrorCode;
        private String errorMsg;
    }

}
