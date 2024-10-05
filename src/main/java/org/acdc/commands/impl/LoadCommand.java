package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String sessionId = arguments.get(0);
        File preferenceFile = new File(String.format("%s.txt", sessionId));

        if (!preferenceFile.exists()) {
            out.println("404 Session not found");
            return true;
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(preferenceFile))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    context.add(parts[0], parts[1]);
                }
            }

            StringBuilder response = new StringBuilder("200 Preferences loaded:");
            context.getKeys().forEach(key -> response.append(String.format(" %s %s", key, context.get(key))));

            out.println(response.toString());
        } catch (IOException e) {
            out.println("500 Internal Server Error: Unable to load preferences");
        }

        return true;
    }
}