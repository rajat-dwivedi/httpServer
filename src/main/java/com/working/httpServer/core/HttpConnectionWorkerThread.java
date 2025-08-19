package com.working.httpServer.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    Socket socket;

    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            //InputStreamReader converts raw bytes into characters.
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            int _byte;

            while((_byte = inputStream.read())>=0){
                System.out.println((char)_byte);
            }

      
            String html = "<html><body><h1>Hello, World! This is the second hard coded dumb output</h1></body></html>";
            final String CRLF = "\r\n"; // Line separator in HTTP
            // Build HTTP response
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                            "Content-Type: text/html; charset=UTF-8" + CRLF +
                            "Content-Length: " + html.length() + CRLF +
                            CRLF +
                            html;

            outputStream.write(response.getBytes());


            inputStream.close();
            outputStream.close();
            socket.close();

            LOGGER.info("connection processing finished");
        }catch (Exception e){
            e.printStackTrace();
            //handle later
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
