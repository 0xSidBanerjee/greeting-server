package org.acdc.Utils;

import lombok.extern.slf4j.Slf4j;
import org.acdc.commands.CommandInvoker;
import org.acdc.commands.impl.QuitCommandException;

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
    private CommandInvoker invoker;

    public ClientHandler(Socket clientSocket, int timeout) {
        this.clientSocket = clientSocket;
        this.timeout = timeout;
        this.invoker = new CommandInvoker();
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            try {
                SessionContext context = new SessionContext();
                clientSocket.setSoTimeout(timeout * 1000);

                out.println("200 Server ready");

                String inputLine;

                while ((inputLine = in.readLine()) != null) {

                        if(!this.invoker.executeCommand(ParseUtil.parseInput(inputLine), in, out, context)){
                            return;
                        }

                }
            } catch (SocketTimeoutException e) {
                log.warn("Client connection timed out: {}", clientSocket.getRemoteSocketAddress());
                out.println("408 Request Timeout");
            }
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
