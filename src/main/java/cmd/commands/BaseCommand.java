package cmd.commands;

import cmd.commands.cd.CDCommand;
import cmd.commands.copy.CopyCommand;
import cmd.commands.date.DateCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import cmd.commands.file.FileCommand;
import cmd.commands.find.FindCommand;
import cmd.commands.hash.HashCommand;
import cmd.commands.mkdir.MkDirCommand;
import cmd.commands.move.MoveCommand;
import cmd.commands.rename.RenameCommand;
import cmd.commands.web.WebCommand;
import picocli.CommandLine.Command;

/**
 * Base Command Class
 * <p/>
 * This parent command class is meant to register other commands as subcommands. This can be done using the subcommands
 * parameter of the @Command annotation.
 * <p/>
 * For a deeper understanding of how this is tied together please check the picocli documentation.
 *
 * @see <a href="https://picocli.info/">picocli Documentation</a>
 */
@Command(name = "cmd", description = "base command", mixinStandardHelpOptions = true, subcommands = {DirCommand.class,
        DelCommand.class, CopyCommand.class, CDCommand.class, MoveCommand.class, FindCommand.class, FileCommand.class, RenameCommand.class, MkDirCommand.class, DateCommand.class, HashCommand.class, WebCommand.class})
public class BaseCommand implements Runnable {
    public BaseCommand() {

        /* intentionally empty */
    }

    @Override
    public void run() {

        /* intentionally empty */
    }

}
