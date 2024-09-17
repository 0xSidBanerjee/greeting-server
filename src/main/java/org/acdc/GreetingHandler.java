package org.acdc;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class GreetingHandler implements Runnable {
    private Socket clientSocket;
    private String name;
    private String location;
    public GreetingHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("201 Server ready");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                log.info("Received from client: {}", inputLine);

                if (inputLine.startsWith("NAME ")) {
                    name = inputLine.substring(5);
                    out.println("201 NAME ok");
                }

                else if (inputLine.startsWith("LOCATION ")) {
                    location = inputLine.substring(9);
                    log.info("Location {}", location);
                    out.println("201 LOCATION ok");
                }

                else if (inputLine.equalsIgnoreCase("GREET")) {
                    if (name != null && location != null) {
                        out.println("Hello " + name + " of " + location);
                    } else {
                        out.println("400 Error: Please provide both name and location");
                    }
                }

                else if (inputLine.equalsIgnoreCase("QUIT")) {
                    out.println("201 QUIT ok");
                    out.println("Bye");
                    clientSocket.close();
                }

                else {
                    out.println("Invalid Command");
                }
            }
        } catch (IOException e) {
            log.error("ERROR: {}", e.getMessage());
        }

    }
}
