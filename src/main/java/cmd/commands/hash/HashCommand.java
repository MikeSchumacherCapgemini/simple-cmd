package cmd.commands.hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@CommandLine.Command(name = "hash", description = "hash a file to validate it", mixinStandardHelpOptions = true)
public class HashCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(cmd.commands.hash.HashCommand.class);

    @CommandLine.Parameters(paramLabel = "<path>",
            description = "file to be hashed, it will take only the first path")
    private String[] paths;

    @Override
    public void run() {
        try {
            if (paths == null || paths[0] == null || paths[0].isEmpty()){
                System.out.println("You should add a path");
                return;
            }
            File file = new File(paths[0]);
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] result = md.digest(Files.readAllBytes(file.toPath()));

            BigInteger bigInt = new BigInteger(1,result);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
            System.out.println("Hash text of the file is: " + hashtext);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
