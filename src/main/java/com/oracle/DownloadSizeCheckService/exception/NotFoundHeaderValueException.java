package com.oracle.DownloadSizeCheckService.exception;

public class NotFoundHeaderValueException extends RuntimeException {

    private final static String MESSAGE = "Request '%s' does not contains Content-Length header!";

    public NotFoundHeaderValueException(String message) {
        super(String.format(MESSAGE, message));
    }
}
