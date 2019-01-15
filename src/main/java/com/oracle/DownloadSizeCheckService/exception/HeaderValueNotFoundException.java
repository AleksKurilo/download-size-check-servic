package com.oracle.DownloadSizeCheckService.exception;

public class HeaderValueNotFoundException extends RuntimeException {

    private final static String MESSAGE_TEMPLATE = "Request does not contains Content-Length header!";

    public HeaderValueNotFoundException() {
        super(MESSAGE_TEMPLATE);
    }
}
