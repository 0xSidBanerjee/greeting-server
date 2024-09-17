package org.acdc;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Main {

    @Parameter(names = {"--port", "-p"}, description = "Port to listen on")
    private int port = 6666;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        JCommander commander = JCommander.newBuilder()
                .addObject(main)
                .build();

        commander.parse(args);
        main.run();
    }

    private void run() throws IOException {
        log.info("Starting greeting server on port {}", port);
        GreetingServer server = new GreetingServer(port);
        server.start();
    }
}