package org.acdc;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class clientHandler implements Runnable {
    private Socket clientSocket;
    private int timeout;
    private String name;
    private String location;
    public clientHandler(Socket clientSocket, int timeout) {
        this.clientSocket = clientSocket;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("200 Server ready sent to client");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                log.info("Received from client: {}", inputLine);

                if (inputLine.startsWith("NAME ")) {
                    name = inputLine.substring(5);
                    if(name != ""){
                        out.println("201 NAME ok");
                    }
                    else {
                        out.println("400 Bad Request");
                    }
                }

                else if (inputLine.startsWith("LOCATION ")) {
                    location = inputLine.substring(9);
                    log.info("Location {}", location);
                    if(location != "") {
                        out.println("201 LOCATION ok");
                    }
                    else {
                        out.println("400 Bad Request");
                    }
                }

                else if (inputLine.equalsIgnoreCase("GREET")) {
                    if (name != null && location != null) {
                        out.println("Hello " + name + " of " + location);
                    } else {
                        out.println("401 Missing Information");
                    }
                }

                else if (inputLine.equalsIgnoreCase("QUIT")) {
                    out.println(" 202 Bye");
                    clientSocket.close();
                }

                else {
                    out.println("400 Bad Request");
                }
            }
        } catch (IOException e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }
}
