package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class StatsCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String name = context.get("NAME");
        String location = context.get("LOCATION");
        String commandCount = context.get("COMMANDS");

        out.println(String.format("202 STATS NAME: %s, LOCATION: %s, COMMANDS: %s", name, location, commandCount));
        return true;
    }
}
