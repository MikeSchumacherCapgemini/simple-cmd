package cmd.commands.find;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmd.SimpleCmd;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * "Find filetyppe" command class
 * <p/>
 * Executing the command lists files of the selected type
 */
@Command(name = "find", description = "Displays files with the provided extension", mixinStandardHelpOptions = true)
public class FindCommand implements Runnable {

  public static final Logger LOG = LoggerFactory.getLogger(cmd.commands.find.FindCommand.class);

  @Parameters(index = "0", description = "extension to filter for")
  private String filterExtension;

  public FindCommand() {

    /* intentionally empty */
  }

  @Override
  public void run() {

    listFilesInDirectory(SimpleCmd.getCurrentLocation());
  }

  private void listFilesInDirectory(File directory) {

    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (null != files) {
        Stream.of(files).forEach(this::printLine);
      }
    }
  }

  private void printLine(File f) {

    Optional<String> fileEnding = getExtensionByStringHandling(f.getName());
    if (fileEnding.isPresent() && fileEnding.get().equals(this.filterExtension)) {
      if (!f.isDirectory()) {

        try {
          LOG.info("{}\t{} bytes\n", f.getAbsolutePath(), Files.size(f.toPath()));
        } catch (IOException e) {
          LOG.error("Programming error, don't mention it pls");
        }
      }
    }
  }

  public Optional<String> getExtensionByStringHandling(String filename) {

    return Optional.ofNullable(filename).filter(f -> f.contains("."))
        .map(f -> f.substring(filename.lastIndexOf(".") + 1));
  }
}
