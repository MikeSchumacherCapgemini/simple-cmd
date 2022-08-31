package cmd.commands.mkdir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "mkdir", description = "Displays files and directories of current working directory", mixinStandardHelpOptions = true)
public class MkDirCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(cmd.commands.mkdir.MkDirCommand.class);

    @CommandLine.Option(names = { "-f", "--files" }, description = "create file")
    private boolean isFile;

    @CommandLine.Option(names = { "-d", "--dir" }, description = "create dir")
    private boolean isDir;

    @CommandLine.Parameters(paramLabel = "<path>",
            description = "Words to be translated into ASCII art.")
    private String[] paths;


    @Override
    public void run() {
        if (paths == null || paths[0] == null || paths[0].isEmpty()){
            System.out.println("You should add a path");
            return;
        }
        try {
            File file = new File(paths[0]);
            if (isFile){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }else {
                file.mkdir();
            }
        }catch (Exception e) {
            System.out.printf("Could not create a {0}, check please again that the path is correct", isFile? "file":"folder");
        }
    }
}
