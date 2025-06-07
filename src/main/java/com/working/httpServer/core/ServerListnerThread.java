package com.working.httpServer.core;

import com.working.httpServer.HttpServer;
import com.working.httpServer.config.Configration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListnerThread extends Thread{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerListnerThread.class);
    private String webroot;
    private int port;
    private ServerSocket serverSocket;

    public ServerListnerThread(String webroot, int port) throws IOException {
        this.webroot = webroot;
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection accepted " + socket.getInetAddress());

                HttpConnectionWorkerThread httpConnectionWorkerThread = new HttpConnectionWorkerThread(socket);
                httpConnectionWorkerThread.start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(!serverSocket.isClosed()){
                try{
                    serverSocket.close();
                }catch (Exception e){
                    //handle later
                }
            }
        }
    }
}
