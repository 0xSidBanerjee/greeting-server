package org.acdc.commands;

import org.acdc.Utils.SessionContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public interface Command {
    boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException;
}
