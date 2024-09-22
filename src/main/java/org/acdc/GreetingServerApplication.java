package org.acdc;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class GreetingServerApplication {

    @Parameter(names = {"--port", "-p"}, description = "Port to listen on")
    private int port = 6666;

    @Parameter(names = {"--timeout", "-t"}, description = "Idle Timeout value for each connection")
    private int timeout = 45;

    public static void main(String[] args) throws IOException {
        GreetingServerApplication app = new GreetingServerApplication();
        JCommander commander = JCommander.newBuilder()
                .addObject(app)
                .build();

        commander.parse(args);
        app.run();
    }

    private void run() throws IOException {
        GreetingServer server = new GreetingServer(port, timeout);
        server.start();
    }
}