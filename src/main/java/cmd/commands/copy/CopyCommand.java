package cmd.commands.copy;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * "Copy File" command class
 * <p/>
 * Executing the command copies a file from one point to another.
 * If the file already exists in the target destination, the existing file is overwritten.
 */
@Command(
        name = "copy",
        description = "Copy a file",
        mixinStandardHelpOptions = true)
public class CopyCommand implements Runnable {

    @Parameters(index = "0", description = "path of the file to copy")
    private File source;

    @Parameters(index = "1", description = "path to copy file to")
    private File target;

    public CopyCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        try {
            if(source.exists()) {
                if(!target.exists()) {
                    Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }else{
                    Scanner myObj = new Scanner(System.in);
                    System.out.println("Do you want to overwrite this file? " + target.getName() + " Y/N");
                    String userInput = myObj.nextLine();
                    if(userInput.replaceAll("\\s","").equals("Y") || userInput.replaceAll("\\s","").equals("y")){
                        Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }else{
                System.out.println("The file doesn't exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
