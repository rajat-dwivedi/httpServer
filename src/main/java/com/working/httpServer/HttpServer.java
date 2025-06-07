package com.working.httpServer;

import com.working.httpServer.config.ConfigManager;
import com.working.httpServer.config.Configration;
import com.working.httpServer.core.ServerListnerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {

        LOGGER.info("server starting");
        ConfigManager.getInstance().loadConfig("src/main/resources/http.json");
        Configration conf = ConfigManager.getInstance().getCurrentConfig();
        LOGGER.info("current port "+ conf.getPort());
        LOGGER.info("current webroot "+conf.getWebroot());
//        System.out.println("Current port "+conf.getPort());
//        System.out.println("Current webRoot "+conf.getWebroot());

        try {
            ServerListnerThread serverListnerThread = new ServerListnerThread(conf.getWebroot(), conf.getPort());
            serverListnerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
