package org.acdc;

import java.util.Arrays;
import java.util.List;

public class ParseUtil {
    public static GreetingCommand parseInput(String line) {
        List<String> components = Arrays.stream(line.split(" ")).toList();
        return GreetingCommand.builder()
                .name(components.get(0))
                .arguments(components.subList(1, components.size()))
                .build();
    }
}
