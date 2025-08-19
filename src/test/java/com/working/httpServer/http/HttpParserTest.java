package com.working.httpServer.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass(){
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
       HttpRequest request = null;
       try {
        request = httpParser.parseHttpReequest(generateValidGETTestCase());
       } catch (HttpParsingException e) {
        fail();
       }

       assertNotNull(request);
       assertEquals(request.getMethod(), HttpMethod.GET);
       assertEquals(request.getRequestTarget(),"/");
    }

//    @Test
//    void parseHttpRequestBadMethod() {
//        try {
//         HttpRequest request = httpParser.parseHttpReequest(generateBadTestCaseMethod1());
//         fail();
//        } catch (HttpParsingException e) {
//         // TODO Auto-generated catch block
//         assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
//        }
//    }

    private InputStream generateValidGETTestCase(){
        String rawData = "GET / HTTP/1.1" + "\r\n" +
                "Host: localhost:8080" + "\r\n" +
                "Connection: keep-alive" + "\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"136\", \"Google Chrome\";v=\"136\", \"Not.A/Brand\";v=\"99\"" + "\r\n" +
                "sec-ch-ua-mobile: ?0" + "\r\n" +
                "sec-ch-ua-platform: \"macOS\"" + "\r\n" +
                "Upgrade-Insecure-Requests: 1" + "\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36" + "\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7" + "\r\n" +
                "Sec-Fetch-Site: none" + "\r\n" +
                "Sec-Fetch-Mode: navigate" + "\r\n" +
                "Sec-Fetch-User: ?1" + "\r\n" +
                "Sec-Fetch-Dest: document" + "\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd" + "\r\n" +
                "Accept-Language: en-US,en;q=0.9,hi;q=0.8" + "\r\n" +"\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseMethod1(){
        String rawData = "GET / HTTP/1.1" + "\r\n" +
                "Host: localhost:8080" + "\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd" + "\r\n" +
                "Accept-Language: en-US,en;q=0.9,hi;q=0.8" + "\r\n" +"\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );
        return inputStream;
    }
}