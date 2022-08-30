package cmd.commands.move;

import cmd.commands.copy.CopyCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Base Command Class
 * <p/>
 * This parent command class is meant to register other commands as subcommands.
 * This can be done using the subcommands parameter of the @Command annotation.
 * <p/>
 * For a deeper understanding of how this is tied together please check the picocli documentation.
 * @see <a href="https://picocli.info/">picocli Documentation</a>
 */
@CommandLine.Command(
        name = "move",
        description = "move command",
        mixinStandardHelpOptions = true,
        subcommands = {DirCommand.class, DelCommand.class, CopyCommand.class})
public class MoveCommand implements  Runnable{

    @CommandLine.Parameters(index = "0", description = "path of the file to copy")
    private File source;

    @CommandLine.Parameters(index = "1", description = "path to copy file to")
    private File target;

    public MoveCommand() {
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


