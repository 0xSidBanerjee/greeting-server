package org.acdc.Utils;

import java.util.HashMap;
import java.util.Map;

public class SessionContext {
    private final Map<String, String> attributes = new HashMap<String, String>();
    public void add(String key, String value) {
        attributes.put(key, value);
    }

    public String get(String key) {
        return attributes.getOrDefault(key, "");
    }
}
