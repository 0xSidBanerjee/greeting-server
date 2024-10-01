package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class QuitCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) {
        String farewellMessage = context.get("FAREWELL");
        out.println(String.format("202 %s", farewellMessage));

        return false;
    }
}
