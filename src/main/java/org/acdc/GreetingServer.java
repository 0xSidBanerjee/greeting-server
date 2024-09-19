package org.acdc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class GreetingServer {
    private int port;
    private int timeout;

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true) {
                log.info("Starting server on port {} with timeout {}", port, timeout);
                Socket clientSocket = serverSocket.accept();
                log.info("New connection from {}", clientSocket.getRemoteSocketAddress());
                Thread thread = new Thread(new clientHandler(clientSocket, timeout));
                thread.start();
            }
        }
    }
}
