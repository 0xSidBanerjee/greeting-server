package org.acdc.commands.impl;

import org.acdc.Utils.SessionContext;
import org.acdc.commands.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class QuitCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) {
        out.println("202 Bye");
        return false;

    }
}
