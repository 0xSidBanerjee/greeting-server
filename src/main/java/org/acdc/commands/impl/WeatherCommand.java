package org.acdc.commands.impl;

import com.github.javafaker.Faker;
import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class WeatherCommand implements Command {
    private final Faker faker  = new Faker();
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String location = context.get("LOCATION");
        if (location.isEmpty()) {
            out.println("401 Location not set");
            return true;
        }

        String weather = faker.weather().description();
        int temperature = faker.number().numberBetween(15, 35);

        out.println("202 WEATHER " + weather + " " + temperature + "°C");
        return true;
    }
}
