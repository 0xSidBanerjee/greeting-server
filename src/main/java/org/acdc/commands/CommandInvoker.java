package org.acdc.commands;

import org.acdc.Utils.GreetingCommand;
import org.acdc.Utils.SessionContext;
import org.acdc.commands.impl.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.in;
import static java.lang.System.out;

public class CommandInvoker {

    private final Map<String, Command> commandMap = new HashMap<String, Command>();

    public CommandInvoker() {
        commandMap.put("QUIT", new QuitCommand());
        commandMap.put("GREET", new GreetCommand());
        commandMap.put("NAME", new NameCommand());
        commandMap.put("LOCATION", new LocationCommand());
    }

    public boolean executeCommand(GreetingCommand parsedCommand, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String commandName = parsedCommand.getName();
        Command command = commandMap.getOrDefault(commandName, new DefaultCommand());
        boolean canContinue = command.execute(parsedCommand.getArguments(), in, out, context);
        return canContinue;
    }
}
