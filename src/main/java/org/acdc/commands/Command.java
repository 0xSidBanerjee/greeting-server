package org.acdc.commands;

import org.acdc.SessionContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public interface Command {
    boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException;
}
