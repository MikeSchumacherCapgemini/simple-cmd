package cmd.commands.date;

import cmd.commands.copy.CopyCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import picocli.CommandLine;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@CommandLine.Command(
        name = "date",
        description = "date command",
        mixinStandardHelpOptions = true,
        subcommands = {DirCommand.class, DelCommand.class, CopyCommand.class})
public class DateCommand  implements  Runnable{
    @CommandLine.Option(names = { "-d"}, description = "print only date")
    private boolean dateOnly;
    @CommandLine.Option(names = { "-t"}, description = "print only time")
    private boolean timeOnly;

    @CommandLine.Parameters(index = "0", defaultValue = "yyyy-MM-dd 'at' HH:mm:ss z", description = "date Format, please separate through an underscore")
    private String format;

    public DateCommand(){
        /* intentionally empty */
    }

    @Override
    public void run() {
        format = format.replaceAll("_", " ");
        SimpleDateFormat formatter= new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        String output = formatter.format(date);
        if(dateOnly){
            System.out.println(output.substring(0,10));
            return;
        }
        if (timeOnly) {
            System.out.println(output.substring(14, 21));
            return;
        }
        System.out.println(output);
    }
}
