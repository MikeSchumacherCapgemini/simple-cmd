package cmd.commands.date;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import cmd.commands.dir.DirCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateCommandTest extends AbstractCommandTest {

    @Test
    void testDateReceived(){
        // given
        //prepareTestFolder(tempDir);
        //SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {""};
        DateCommand dateCommand = CommandLine.populateCommand(new DateCommand(), args);
        // when
        dateCommand.run();
        // then
        //String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
        String actual = getOutputStream().toString();
        assertTrue(!actual.isEmpty());
    }
}
