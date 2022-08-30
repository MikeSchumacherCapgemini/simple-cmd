package cmd.commands.del;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * "Delete File" command class
 * <p/>
 * Executing the command deletes a file, which is denoted by the parameter.
 */
@Command(
        name = "del",
        description = "Delete a file",
        mixinStandardHelpOptions = true)
public class DelCommand implements Runnable {

    public static final Logger LOG = LoggerFactory.getLogger(DelCommand.class);

    @Parameters(index = "0", description = "path of the file to delete")
    private File file;

    public DelCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        String absolutePath = file.getAbsolutePath();
        boolean delete = false;
        try {
            if(file.exists()){
                Scanner myObj = new Scanner(System.in);
                System.out.println("Do you want to Delete this file? " + file.getName() + " Y/N");
                String userInput = myObj.nextLine();
                if(userInput.replaceAll("\\s","").equals("Y") || userInput.replaceAll("\\s","").equals("y")){
                    delete = Files.deleteIfExists(file.toPath());
                } else {
                    LOG.info("Recorded input was: {} and the file was not deleted", userInput);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOG.info("{} was {} deleted\n", absolutePath, (delete ? "successfully" : "not"));
    }
}
