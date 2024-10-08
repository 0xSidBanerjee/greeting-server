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
    private String motd;

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            log.info("Starting server on port {} with timeout {}", port, timeout);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                log.info("New connection from {}", clientSocket.getRemoteSocketAddress());
                Thread thread = new Thread(new ClientHandler(clientSocket, timeout, motd));
                thread.start();
            }
        }
    }
}
