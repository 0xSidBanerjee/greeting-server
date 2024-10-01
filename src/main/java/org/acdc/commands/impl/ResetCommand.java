package org.acdc.commands.impl;

import org.acdc.SessionContext;
import org.acdc.commands.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class ResetCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext context) throws IOException {
        String sessionId = context.get("SESSION_ID");
        if (sessionId.isEmpty()) {
            out.println("401 Session not identified");
            return true;
        }

        context.clear();

        File preferenceFile = new File(String.format("%s.txt", sessionId));
        if (preferenceFile.exists() && preferenceFile.delete()) {
            out.println("200 Preferences reset");
        } else if (!preferenceFile.exists()) {
            out.println("200 Preferences reset (no saved preferences found)");
        } else {
            out.println("500 Internal Server Error: Unable to reset preferences");
        }

        return true;
    }
}
