import org.acdc.ParseUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParseUtilTest {
    @DisplayName("Successfully parse a single word command")
    @Test
    void testParseSingleWordCommand() throws Exception {
        var cmd = ParseUtil.parseInput("GREET");
        assertNotNull(cmd);
        assertEquals("GREET", cmd.getName());
        assertEquals(0, cmd.getArguments().size());
    }

    @DisplayName("Succfully parse a multiple word command")
    @Test
    void testParseMultipleWordCommand() throws Exception{
        var cmd = ParseUtil.parseInput("NAME Asterix the Gaul");
        assertNotNull(cmd);
        assertEquals("NAME", cmd.getName());
        assertEquals(3, cmd.getArguments().size());
    }
}
