package com.banorte.logs.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }
}
