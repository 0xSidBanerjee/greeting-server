package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import java.util.TimeZone;

public class TimezoneCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String timezone = String.join(" ", arguments);
        if (isValidTimezone(timezone)) {
           context.add("TIMEZONE", timezone);
           out.println("201 TIMEZONE ok");
        } else {
            out.println("400 Bad Request");
        }
        return true;
    }

    private boolean isValidTimezone(String timezone) {
        return TimeZone.getTimeZone(timezone) != null;
    }
}
