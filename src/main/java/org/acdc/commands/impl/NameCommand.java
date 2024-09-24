package org.acdc.commands.impl;

import org.acdc.Utils.SessionContext;
import org.acdc.commands.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class NameCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) {
        String name = String.join(" ", arguments);
        context.add("NAME", name);
        out.println("201 Name ok");
        return true;
    }
}
