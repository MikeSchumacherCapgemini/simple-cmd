package cmd.commands.dir;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmd.SimpleCmd;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * "List Directory" command class
 * <p/>
 * Executing the command lists all the files and folders in the current working directory.
 *
 * @see SimpleCmd#getCurrentLocation()
 * @see SimpleCmd#setCurrentLocation(File)
 */
@Command(name = "dir", description = "Displays files and directories of current working directory", mixinStandardHelpOptions = true)
public class DirCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @Option(names = { "-f", "--files" }, description = "print only file names, directories will not be listed")
  private boolean filesOnly;

  @Option(names = { "-s", "--sort" }, description = "possible values are {asc, desc} for ascending / descending order")
  private String sortOrder;

  @Option(names = { "-c", "--check" }, description = "check if the given path is a file or directory")
  private boolean fileCheck;

  @Option(names = { "-short" }, description = "gives the relative path to the given directory")
  private boolean relativePath;

  @Parameters(index = "0", defaultValue = "", description = "path of the directory to show, leave blank for current directory")
  private File externalDirectory;

  public DirCommand() {

    /* intentionally empty */
  }

  @Override
  public void run() {

    if (!this.externalDirectory.exists()) {
      if (this.fileCheck) {
        LOG.info("This is a directory!\n");
        return;
      }
      listFilesInDirectory(SimpleCmd.getCurrentLocation());
    } else {
      if (this.fileCheck) {
        if (this.externalDirectory.isFile()) {
          LOG.info("The given path represents a file\n");
          return;
        } else {
          LOG.info("The given path represents a directory\n");
          return;
        }
      }
      listFilesInDirectory(this.externalDirectory);
    }
  }

  private void listFilesInDirectory(File directory) {

    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (null != files) {
        Stream.of(files).sorted(getFileListComparator()).forEach(this::printLine);
      }
    } else {
      LOG.info("{}\n", directory.getAbsolutePath());
    }
  }

  private Comparator<String> getSortOrderAwareFileNameComparator(final String sortOrder) {

    if ("desc".equals(sortOrder)) {
      return Collections.reverseOrder(String::compareToIgnoreCase);
    } else if ("asc".equals(sortOrder)) {
      return String::compareToIgnoreCase;
    } else {
      return (fileName1, fileName2) -> 0;
    }
  }

  private Comparator<File> getFileListComparator() {

    return Comparator.comparing(File::getName, getSortOrderAwareFileNameComparator(this.sortOrder));
  }

  private void printLine(File f) {

    if (this.filesOnly) {
      if (!f.isDirectory()) {
        if (this.relativePath) {
          LOG.info("{}\n", getRelativePath(SimpleCmd.getCurrentLocation().getAbsolutePath(), f.getAbsolutePath()));
        } else {
          LOG.info("{}\n", f.getAbsolutePath());
        }
      }
    } else {
      if (this.relativePath) {
        LOG.info("{}\n", getRelativePath(SimpleCmd.getCurrentLocation().getAbsolutePath(), f.getAbsolutePath()));
      } else {
        LOG.info("{}\n", f.getAbsolutePath());
      }
    }
  }

  private Path getRelativePath(String path1, String path2) {

    Path first = Paths.get(path1);
    Path second = Paths.get(path2);
    return first.relativize(second);
  }
}
