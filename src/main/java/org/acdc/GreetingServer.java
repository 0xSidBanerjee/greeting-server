package org.acdc;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class GreetingServer {
    private int port;
    public GreetingServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true) {
                Socket clientSocket = serverSocket.accept();
                log.info("Client connected: {}", clientSocket.getRemoteSocketAddress());
                Thread thread = new Thread(new GreetingHandler(clientSocket));
                thread.start();
            }
        }
    }
}
