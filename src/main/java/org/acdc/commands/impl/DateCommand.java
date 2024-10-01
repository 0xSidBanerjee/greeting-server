package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String format = arguments.get(0);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            String formattedDate = LocalDateTime.now().format(formatter);
            out.printf("202 DATE %s", formattedDate);
        } catch (IllegalArgumentException e) {
            out.println("400 Bad Request - Invalid format");
        }
        return true;
    }
}
