package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class GreetCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) {
        String name = context.get("NAME");
        String location = context.get("LOCATION");
        String timezone = context.get("TIMEZONE");
        String greeting = generateGreetingBasedOnTime(timezone);

        out.printf("%s! %s of %s%n", greeting, name, location);
        return true;
    }

    private String generateGreetingBasedOnTime(String timezone) {
        ZoneId zoneId = ZoneId.of(timezone == null || timezone.isEmpty() ? "UTC" : timezone);
        int currentTimeHour = LocalDateTime.now(zoneId).getHour();

        String timeBasedGreeting;
        if (currentTimeHour >= 4 && currentTimeHour < 12) {
            timeBasedGreeting = "おはよう (Ohayō)";
        } else if (currentTimeHour >= 12 && currentTimeHour < 18) {
            timeBasedGreeting = "こんにちは (Kon'nichiwa)";
        } else if (currentTimeHour > 18 && currentTimeHour < 21) {
            timeBasedGreeting = "こんばんは (Konbanwa)";
        } else {
            timeBasedGreeting = "おやすみ (Oyasumi)";
        }
        return timeBasedGreeting;
    }
}
