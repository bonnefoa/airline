package xlsParser;

import jxl.read.biff.BiffException;
import xlsParser.parser.Handler;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Main method
 */
public class App {
    private static Handler handler;

    public static void main(String[] args) throws BiffException, IOException, SQLException {
        if (args.length < 2) {
            System.out.println("Usage :");
            System.out.println("[Script de creation sql de la base] [Donnees sous format xsl] ");
        } else {
            System.out.println("Parse :");
            handler = new Handler(args[0], args[1]);
            handler.execute();
            System.out.println("Parse finit");
        }
        System.exit(0);
    }
}
