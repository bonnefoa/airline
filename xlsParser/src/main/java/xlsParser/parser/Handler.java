/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
