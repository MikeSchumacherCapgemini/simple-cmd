package cmd.commands.date;

import cmd.commands.copy.CopyCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import picocli.CommandLine;

import java.text.SimpleDateFormat;
import java.util.Date;

@CommandLine.Command(
        name = "date",
        description = "date command",
        mixinStandardHelpOptions = true,
        subcommands = {DirCommand.class, DelCommand.class, CopyCommand.class})
public class DateCommand  implements  Runnable{
    public DateCommand(){
        /* intentionally empty */
    }

    @Override
    public void run() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
    }
}
