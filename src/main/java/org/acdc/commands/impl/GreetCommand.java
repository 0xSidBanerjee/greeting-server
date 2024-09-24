package org.acdc.commands.impl;

import org.acdc.Utils.SessionContext;
import org.acdc.commands.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class GreetCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) {
        String name = context.get("NAME");
        String location = context.get("LOCATION");
        out.printf("Hello %s of %s%n", name, location);
        return true;
    }
}
