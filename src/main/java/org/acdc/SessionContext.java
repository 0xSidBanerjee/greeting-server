package org.acdc;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SessionContext {
    private final Map<String, String> attributes;

    public SessionContext() {
        attributes = new HashMap<String, String>();
    }

    @Getter
    private int commandCount = 0;

    public void add(String key, String value) {
        attributes.put(key, value);
    }

    public String get(String key) {
        return attributes.getOrDefault(key, "");
    }

    public Set<String> getKeys() {
        return attributes.keySet();
    }

    public void clear() {
        attributes.clear();
    }

    public void incrementCommandCount() {
        commandCount++;
    }
}
