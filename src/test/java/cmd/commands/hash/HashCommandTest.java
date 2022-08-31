package cmd.commands.hash;

import cmd.commands.AbstractCommandTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashCommandTest extends AbstractCommandTest {
    private final String fileName = "abc.txt";

    @Test
    void shouldHashFile() throws IOException {
        // given
        File file = new File(fileName);
        file.createNewFile();
        HashCommand hashCommand = CommandLine.populateCommand(new HashCommand(), fileName);

        // when
        hashCommand.run();

        // then
        String expectedHash = "d41d8cd98f00b204e9800998ecf8427e";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expectedHash));

    }
}
