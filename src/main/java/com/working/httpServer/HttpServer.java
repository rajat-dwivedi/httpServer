package com.working.httpServer;

import com.working.httpServer.config.ConfigManager;
import com.working.httpServer.config.Configration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("starting server");
        ConfigManager.getInstance().loadConfig("src/main/resources/http.json");
        Configration conf = ConfigManager.getInstance().getCurrentConfig();

        System.out.println("Current port "+conf.getPort());
        System.out.println("Current webRoot "+conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //we would read

            //we would write

            // HTML content
            String html = "<html><body><h1>Hello, World! This is the initial hard coded dumb output</h1></body></html>";
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
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
