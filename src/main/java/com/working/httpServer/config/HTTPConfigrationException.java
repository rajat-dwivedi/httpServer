package com.working.httpServer.config;

public class HTTPConfigrationException extends RuntimeException{
    public HTTPConfigrationException() {
        super();
    }

    public HTTPConfigrationException(String message) {
        super(message);
    }

    public HTTPConfigrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HTTPConfigrationException(Throwable cause) {
        super(cause);
    }
 

}
