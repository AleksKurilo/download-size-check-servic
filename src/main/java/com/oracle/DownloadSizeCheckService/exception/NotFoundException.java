package com.oracle.DownloadSizeCheckService.exception;

public class NotFoundException extends RuntimeException{

    private final static String MESSAGE = "Request '%s' does not contains Content-Length header!";

    private final String message;

    public NotFoundException(String message) {
        super(String.format(MESSAGE, message));
        this.message = message;
    }
}
