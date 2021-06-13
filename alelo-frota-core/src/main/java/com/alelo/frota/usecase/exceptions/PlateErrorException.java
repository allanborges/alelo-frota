package com.alelo.frota.usecase.exceptions;

public class PlateErrorException extends RuntimeException{

    public PlateErrorException(String message) {
        super(message);
    }

    public PlateErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
