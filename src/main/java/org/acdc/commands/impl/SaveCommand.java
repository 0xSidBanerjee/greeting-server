package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import java.util.Set;

public class SaveCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String sessionId = context.get("SESSION_ID");
        if (sessionId.isEmpty()) {
            out.println("401 session not identified");
            return true;
        }
        File preferenceFile = new File(String.format("%s.txt", sessionId));
        try (PrintWriter fileWriter = new PrintWriter(preferenceFile)) {
            Set<String> keys = context.getKeys();
            for (String key : keys) {
                String value = context.get(key);
                fileWriter.printf("%s : %s%n", key, value);
            }
        }
        out.println("200 Preferences Saved");
        return true;
    }
}
