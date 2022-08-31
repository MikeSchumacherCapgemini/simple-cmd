package cmd.commands.rename;

import cmd.commands.copy.CopyCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
@CommandLine.Command(
        name = "rename",
        description = "rename command",
        mixinStandardHelpOptions = true,
        subcommands = {DirCommand.class, DelCommand.class, CopyCommand.class})
public class RenameCommand  implements  Runnable{

    @CommandLine.Parameters(index = "0", description = "path of the file to copy")
    private File source;

    @CommandLine.Parameters(index = "1", description = "path to copy file to")
    private File target;

    public RenameCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        try {
            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean delete = false;
        while(delete != true) {
            try {
                delete = Files.deleteIfExists(source.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
