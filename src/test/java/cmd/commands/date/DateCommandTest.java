package cmd.commands.date;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cmd.commands.AbstractCommandTest;
import picocli.CommandLine;

public class DateCommandTest extends AbstractCommandTest {

  @Test
  void testDateReceived() {

    // given
    // prepareTestFolder(tempDir);
    // SimpleCmd.setCurrentLocation(tempDir.toFile());
    DateCommand dateCommand = CommandLine.populateCommand(new DateCommand());
    // when
    dateCommand.run();
    // then
    // String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
    String actual = getOutputStream().toString();
    assertTrue(!actual.isEmpty());
  }
}
