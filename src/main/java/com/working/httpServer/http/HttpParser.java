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
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request = new HttpRequest();
        try {
            parseRequestLine(reader,request);
        }catch (Exception e){
            e.printStackTrace();
        }
        parseHeader(reader,request);
        parseBody(reader,request);
        return request;
    }

    private void parseHeader(InputStreamReader inputStream, HttpRequest request) {
    }

    private void parseBody(InputStreamReader inputStream, HttpRequest request) {

    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {
        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        int _byte = 0;
        StringBuilder processingDataBuffer = new StringBuilder();
        while((_byte=reader.read())>=0){
            //reaeding the carriage return (CR) and line feed (LF)
            if(_byte==CR){
                _byte = reader.read();
                if(_byte==LF){
                    LOGGER.debug("request line version to process : {}", processingDataBuffer.toString() );
                    return;
                }
            }

            //reading the spaces
            if(_byte==SP){
                //process the previous data
                if(!methodParsed){
                    LOGGER.debug("request line METHOD to process : {}", processingDataBuffer.toString() );
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                }else if(!requestTargetParsed){
                    LOGGER.debug("request line REQ TARGET to process : {}", processingDataBuffer.toString() );
                    requestTargetParsed = true;
                }
                processingDataBuffer.delete(0,processingDataBuffer.length());
            }else{
                processingDataBuffer.append((char)_byte);
            }
        }
    }
}
