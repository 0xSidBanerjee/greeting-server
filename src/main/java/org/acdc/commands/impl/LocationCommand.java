package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class LocationCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) {
        String location = String.join(" ", arguments);
        context.add("LOCATION", location);
        out.println("201 Location ok");
        return true;
    }
}
