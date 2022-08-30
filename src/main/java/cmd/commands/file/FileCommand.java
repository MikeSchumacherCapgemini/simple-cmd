package cmd.commands.file;

import cmd.SimpleCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.*;

/**
 * "Change Directory" command class
 * <p/>
 * Executing the command changes the currently set directory
 */
@Command(
        name = "file",
        description = "Displays the content of the provided file",
        mixinStandardHelpOptions = true)
public class FileCommand implements Runnable {

    public static final Logger LOG = LoggerFactory.getLogger(cmd.commands.file.FileCommand.class);

    @Parameters(index = "0", description = "file to open")
    private File file;

    public FileCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        if (!file.isDirectory()) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            String line;
            while(true)
            {
                try {
                    if (!((line = in.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
            }
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Provided file is a directory");
        }
    }
}
