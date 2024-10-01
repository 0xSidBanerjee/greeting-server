package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class FarewellCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String farewellMessage = String.join(" ", arguments);
        context.add("FAREWELL", farewellMessage);
        out.println("201 FAREWELL ok");
        return true;
    }
}
