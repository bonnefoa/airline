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
        if(args.length<3){
            System.out.println("Usage :");
            System.out.println("Usage :");
        }
        handler = new Handler(args[0], args[1],args[2]);
        handler.execute();
    }
}
