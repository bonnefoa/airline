package xlsParser.parser;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Handler of parser
 */
public class Handler {
    private String databaseName;

    private String fileName;

    private Parser parser;

    public Handler(String databaseName, String fileName) {
        this.databaseName = databaseName;
        this.fileName = fileName;
        parser = new Parser(databaseName);
    }

    public void execute() throws IOException, BiffException, SQLException {
        if (fileName.endsWith(".xls")) {
            parser.parseXls(fileName);
        } else if (fileName.endsWith(".sql")) {
            parser.initTables(fileName);
        }
    }
}
