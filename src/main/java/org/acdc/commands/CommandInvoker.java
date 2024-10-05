package org.acdc.commands;

import org.acdc.GreetingCommand;
import org.acdc.SessionContext;
import org.acdc.commands.impl.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

    private final Map<String, Command> commandMap = new HashMap<String, Command>();

    public CommandInvoker() {
        commandMap.put("QUIT", new QuitCommand());
        commandMap.put("GREET", new GreetCommand());
        commandMap.put("NAME", new NameCommand());
        commandMap.put("LOCATION", new LocationCommand());
        commandMap.put("TIMEZONE", new TimezoneCommand());
        commandMap.put("DATE", new DateCommand());
        commandMap.put("WEATHER", new WeatherCommand());
        commandMap.put("FAREWELL", new FarewellCommand());
        commandMap.put("IDENTIFY", new IdentifyCommand());
        commandMap.put("SAVE", new SaveCommand());
        commandMap.put("COMMANDS", new StatsCommand());
        commandMap.put("LOAD", new LoadCommand());
        commandMap.put("RESET", new ResetCommand());
    }

    public boolean executeCommand(GreetingCommand parsedCommand, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String commandName = parsedCommand.getName();
        Command command = commandMap.getOrDefault(commandName, new DefaultCommand());
        boolean canContinue = command.execute(parsedCommand.getArguments(), in, out, context);
        context.incrementCommandCount();
        return canContinue;
    }
}
