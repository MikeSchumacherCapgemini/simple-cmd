package cmd.commands.cd;

import cmd.SimpleCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;

/**
 * "Change Directory" command class
 * <p/>
 * Executing the command changes the currently set directory
 */
@Command(
        name = "cd",
        description = "Changes currently set directory",
        mixinStandardHelpOptions = true)
public class CDCommand implements Runnable {

    public static final Logger LOG = LoggerFactory.getLogger(CDCommand.class);

    @Parameters(index = "0", description = "path of the directory to move to")
    private File file;

    public CDCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        String oldPath = SimpleCmd.getCurrentLocation().getAbsolutePath();
        if (file.isDirectory()) {
            SimpleCmd.setCurrentLocation(file);
        } else {
            throw new RuntimeException("Provided path is not a directory");
        }
        LOG.info("Current path was {} and now set to {}\n", oldPath, file.getAbsolutePath());
    }
}
