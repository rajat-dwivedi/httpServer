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


    //IOException in case the ServerSocket is not able to be created (port is already in use )
    public ServerListnerThread(String webroot, int port) throws IOException {
        this.webroot = webroot;
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        
        try {
            // Main server loop: continuously listen for incoming client connections
            // Check if server socket is bound to port and not closed before accepting connections
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                // Block and wait for a client to connect, returns a Socket object for the connection
                Socket socket = serverSocket.accept();

                // Log the IP address of the connected client for monitoring purposes
                LOGGER.info("Connection accepted " + socket.getInetAddress());

                // Create a new worker thread to handle this specific client connection
                // This allows the server to handle multiple clients concurrently
                HttpConnectionWorkerThread httpConnectionWorkerThread = new HttpConnectionWorkerThread(socket);
                // Start the worker thread to process the client request independently
                httpConnectionWorkerThread.start();
             
            }
            serverSocket.close();
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
