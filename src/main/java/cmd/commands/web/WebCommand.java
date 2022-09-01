package cmd.commands.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@CommandLine.Command(name = "web", description = "check whether URL is reachable and can open browser for specific URL ", mixinStandardHelpOptions = true)
public class WebCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(cmd.commands.web.WebCommand.class);

    @CommandLine.Option(names = { "-r", "--reach" }, description = "Check if a given URL is reachable")
    private boolean shouldCheckIfReachable;

    @CommandLine.Option(names = { "-o", "--open" }, description = "Open default browser for a given URL")
    private boolean shouldOpenInTheBrowser;

    @CommandLine.Parameters(paramLabel = "<Url>",
            description = "url to check or to open in the browser")
    private String[] url;

    @Override
    public void run() {
        if (url == null || url[0] == null || url[0].isEmpty()){
            System.out.println("You should add an url");
            return;
        }
        if(shouldCheckIfReachable){
            try {
                String reachableMessage = InetAddress.getByName(url[0]).isReachable(1000) ? "reachable" : "not reachable";
                System.out.printf("The given URL is " + reachableMessage + "\n");
            }
            catch (IOException e) {
                System.out.printf("Could not reach the URL\n");;
            }
        }
        else {
            Runtime rt = Runtime.getRuntime();
            String targetUrl = url[0];
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + targetUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
