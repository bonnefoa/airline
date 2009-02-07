package launcher;

import org.glassfish.embed.GlassFish;
import org.glassfish.embed.ScatteredWar;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Launcher of embeddable server
 */
public class Launcher {
    private static int port = 8080;
    private static final String NAME = "airline";


    public static void main(String[] args) {
        try
        {
            GlassFish glassfish = new GlassFish(port);
//            File war = new File("target/"+NAME + ".war");
            ScatteredWar war = new ScatteredWar(NAME,
                    new File("src/main/resources"),
                    new File("target/WEB-INF/web.xml"),
                    Collections.singleton(new File("target/WEB-INF/classes/airline").toURI().toURL()));
            glassfish.deploy(war);
            System.out.println("Ready ...");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
