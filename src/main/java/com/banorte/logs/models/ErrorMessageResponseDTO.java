package com.banorte.logs.models;

import com.banorte.logs.enums.CodigoError;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ErrorMessageResponseDTO {

    private String message;
    private CodigoError codigoError;

    public ErrorMessageResponseDTO(String message, CodigoError codigoError) {
        this.codigoError = codigoError;
        this.message = message;
    }

    public ErrorMessageResponseDTO(Set<? extends ConstraintViolation<?>> violations, CodigoError codigoError) {
        this.codigoError = codigoError;
        this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    public String getMessage() {
        return message;
    }

    public CodigoError getCodigoError() {
        return codigoError;
    }
}
