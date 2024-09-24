package org.acdc.commands.impl;

import org.acdc.Utils.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class DefaultCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        out.println("400 Bad Request");
        return true;
    }
}
