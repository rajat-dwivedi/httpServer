package com.working.httpServer;

import com.working.httpServer.config.ConfigManager;
import com.working.httpServer.config.Configration;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("starting server");
        ConfigManager.getInstance().loadConfig("src/main/resources/http.json");
        Configration conf = ConfigManager.getInstance().getCurrentConfig();

        System.out.println("Current port "+conf.getPort());
        System.out.println("Current webRoot "+conf.getWebroot());
    }
}
