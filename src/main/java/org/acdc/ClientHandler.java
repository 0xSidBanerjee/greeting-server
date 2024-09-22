package org.acdc;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Slf4j
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int timeout;

    public ClientHandler(Socket clientSocket, int timeout) {
        this.clientSocket = clientSocket;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            clientSocket.setSoTimeout(timeout * 1000);

            out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("200 Server ready");

            String inputLine;
            String name = "";
            String location = "";

            while ((inputLine = in.readLine()) != null) {
                GreetingCommand cmd = ParseUtil.parseInput(inputLine);

                switch (cmd.getName().toUpperCase()) {
                    case "NAME":
                        name = String.join(" ", cmd.getArguments());
                        out.println("201 NAME ok");
                        break;

                    case "LOCATION":
                        location = String.join(" ", cmd.getArguments());
                        out.println("201 LOCATION ok");
                        break;

                    case "GREET":
                        out.printf("Hello %s of %s%n", name, location);
                        break;

                    case "QUIT":
                        out.println("202 Bye");
                        return;

                    default:
                        out.println("400 Bad Request");
                        break;
                }
            }
        } catch (SocketTimeoutException e) {
            log.warn("Client connection timed out: {}", clientSocket.getRemoteSocketAddress());
            out.println("408 Request Timeout");
            out.flush();

        } catch (IOException e) {
            log.error("Error in ClientHandler: {}", e.getMessage());
        } finally {
            try {
                clientSocket.close();
                log.info("Closed connection to client {}", clientSocket.getRemoteSocketAddress());
            } catch (IOException e) {
                log.error("Error closing client socket: {}", e.getMessage());
            }
        }
    }
}
