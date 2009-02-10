package xlsParser.parser;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Handler of parser
 */
public class Handler {
    /**
     * DataBase name
     */
    private String databaseName = "airline";

    /**
     * Sql file of the database
     */
    private String sqlFile;

    /**
     * xslFile to parse
     */
    private String xslFile;

    /**
     * parser of files
     */
    private Parser parser;

    /**
     * Constructor with parameters
     *
     * @param sqlFile      sqlFile of the base to create
     * @param xsl          xsl file containings data
     */
    public Handler(String sqlFile, String xsl) {
        this.sqlFile = sqlFile;
        this.xslFile = xsl;
        parser = new ParserImpl(databaseName);
    }

    /**
     * Execute the parse of sql and xsl files
     *
     * @throws IOException
     * @throws BiffException
     * @throws SQLException
     */
    public void execute() throws IOException, BiffException, SQLException {
        parser.initTables(sqlFile);
        parser.parseXls(xslFile);
        parser.disconnect();
    }
}
