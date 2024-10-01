package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

public class IdentifyCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String sessionId = UUID.randomUUID().toString();
        context.add("SESSION_ID", sessionId);
        out.printf("201 Session ID generated: %s%n", sessionId);
        return true;
    }
}
