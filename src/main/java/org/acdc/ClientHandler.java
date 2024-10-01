package org.acdc;

import com.github.lalyos.jfiglet.FigletFont;
import lombok.extern.slf4j.Slf4j;
import org.acdc.Utils.ParseUtil;
import org.acdc.commands.CommandInvoker;

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
    private String motd;

    public ClientHandler(Socket clientSocket, int timeout, String motd) {
        this.clientSocket = clientSocket;
        this.timeout = timeout;
        this.invoker = new CommandInvoker();
        this.motd = motd;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            try {
                SessionContext context = new SessionContext();
                clientSocket.setSoTimeout(timeout * 1000);

                if (!motd.isEmpty()) {
                    String asciiMotd = FigletFont.convertOneLine(motd);
                    out.println(asciiMotd);
                }

                out.println("200 Server ready");

                String inputLine;

                while ((inputLine = in.readLine()) != null) {


                    if (!this.invoker.executeCommand(ParseUtil.parseInput(inputLine), in, out, context)) {
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
