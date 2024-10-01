package org.acdc;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GreetingCommand {
    private String name;
    private List<String> arguments;
}
