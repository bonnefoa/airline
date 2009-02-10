package xlsParser.parser;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Feb 9, 2009
 * Time: 10:20:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Parser {
    void disconnect();

    void initTables(String fileName) throws IOException, SQLException;

    void parseXls(String filename) throws BiffException, IOException, SQLException;
}
