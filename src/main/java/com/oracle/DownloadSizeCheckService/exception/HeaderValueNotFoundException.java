package com.oracle.DownloadSizeCheckService.exception;

public class HeaderValueNotFoundException extends RuntimeException {

    private final static String MESSAGE_TEMPLATE = " %s does not contains %s header!";

    private final String url;
    private final String header;

    public HeaderValueNotFoundException(String url, String header) {
        super(String.format(MESSAGE_TEMPLATE, url, header));
        this.url = url;
        this.header = header;
    }
}
