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
    private String databaseName;

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
     * @param databaseName Name of the hsql database to create
     * @param sqlFile      sqlFile of the base to create
     * @param xsl          xsl file containings data
     */
    public Handler(String databaseName, String sqlFile, String xsl) {
        this.databaseName = databaseName;
        this.sqlFile = sqlFile;
        this.xslFile = xsl;
        parser = new Parser(databaseName);
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
        parser.parseXls(sqlFile);
    }
}
