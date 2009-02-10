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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Parser for xls
 */
public class ParserImpl implements Parser {

    /**
     * Database connection
     */
    private Connection connection;

    /**
     * Constructor initialising the database connection
     *
     * @param databaseName
     */
    public ParserImpl(String databaseName) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:db/" + databaseName, "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeQuery("SHUTDOWN");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise the creation of the tables with the sql file
     *
     * @param fileName name of the sql file
     * @throws IOException
     * @throws SQLException
     */
    public void initTables(String fileName) throws IOException, SQLException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(fileName));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            fileData.append(buf, 0, numRead);
        }
        reader.close();
        Statement statement = connection.createStatement();
        statement.execute(fileData.toString());
    }

    /**
     * Parse the xsl file and fill the databse
     *
     * @param filename xsl file
     * @throws BiffException
     * @throws IOException
     * @throws SQLException
     */
    public void parseXls(String filename) throws BiffException, IOException, SQLException {
        Statement statement = connection.createStatement();
        StringBuilder queryBegin;
        List<String> listColums;
        Workbook workbook = Workbook.getWorkbook(new File(filename));
        for (int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++) {
            Sheet sheet = workbook.getSheet(sheetNumber);
            String table = sheet.getName();
            Cell[] row;
            if (sheet.getRows() > 0) {
                listColums = new LinkedList<String>();
                row = sheet.getRow(0);
                for (Cell cell : row) {
                    String content = cell.getContents();
                    content = content.replaceAll(" ", "");
                    if (content.equalsIgnoreCase("From")) {
                        content = "ORIGIN";
                    } else if (content.equalsIgnoreCase("To")) {
                        content = "DESTINATION";
                    }
                    listColums.add(content);
                }
                queryBegin = new StringBuilder();
                queryBegin.append("INSERT INTO ");
                queryBegin.append(table);
                queryBegin.append(" (");
                int nbrColumns = 0;
                for (String column : listColums) {
                    // Cas de la colomne vide prise en compte
                    if (column.equals("")) {
                        continue;
                    }
                    nbrColumns++;
                    queryBegin.append(column);
                    queryBegin.append(',');
                }
                queryBegin.deleteCharAt(queryBegin.length() - 1);
                queryBegin.append(") VALUES (");
                for (int i = 1; i < sheet.getRows(); i++) {
                    StringBuilder query = new StringBuilder(queryBegin);
                    row = sheet.getRow(i);
                    boolean isEmpty = true;
                    for (int j = 0; j < nbrColumns; j++) {
                        Cell cell = row[j];
                        String contentRow = cell.getContents();
                        if (!contentRow.equals("")) {
                            isEmpty = false;
                        }
                        query.append('\'');
                        if (contentRow.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                            try {
                                contentRow = new Timestamp(new SimpleDateFormat("dd/MM/yy").parse(contentRow).getTime()).toString();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        query.append(contentRow);
                        query.append("',");
                    }
                    if (isEmpty) {
                        continue;
                    }
                    query.deleteCharAt(query.length() - 1);
                    query.append(")");
                    statement.execute(query.toString());
                }
            }
        }
    }
}
