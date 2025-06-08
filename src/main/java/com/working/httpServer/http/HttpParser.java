package com.working.httpServer.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private static final Logger LOGGER =  LoggerFactory.getLogger(HttpParser.class);
    private static final int SP = 0x20;//32
    private static final int CR = 0x0D;//13
    private static final int LF = 0x0A;//10
    public HttpRequest parseHttpReequest(InputStream inputStream){
        InputStreamReader reader = new InputStreamReader(InputStream, StandardCharsets.US_ASCII);
        HttpRequest request = new HttpRequest();
        parseRequestLine(reader,request);
        parseHeader(reader,request);
        parseBody(reader,request);
        return request;
    }

    private void parseHeader(InputStreamReader inputStream, HttpRequest request) {
    }

    private void parseBody(InputStreamReader inputStream, HttpRequest request) {

    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {
        int _byte;
        while((_byte==reader.read())>=0){
            if(_byte==CR){
                _byte = reader.read();
                if(_byte==LF){

                }
            }
        }
    }
}
