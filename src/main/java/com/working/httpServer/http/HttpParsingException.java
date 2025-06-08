package com.working.httpServer.http;

public class HttpParsingException extends Exception{
    private final HttpStatusCode errorCode;
    public HttpParsingException(HttpStatusCode errorCode){
        SUPER(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }
}
