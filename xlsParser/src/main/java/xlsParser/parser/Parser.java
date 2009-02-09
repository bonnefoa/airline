package xlsParser.parser;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Parser for xls
 */
public class Parser {
    private Workbook workbook;

    private Connection connection;

    public Parser(String databaseName) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:db/" + databaseName, "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initTables(String fileName) throws IOException, SQLException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(fileName));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            fileData.append(buf, 0, numRead);
        }
        reader.close();
        Statement statement = connection.createStatement();
        statement.execute(fileData.toString());
    }

    public void parseXls(String filename) throws BiffException, IOException, SQLException {
        Statement statement = connection.createStatement();
        StringBuilder queryBegin;
        List<String> listColums;
        workbook = Workbook.getWorkbook(new File(filename));
        for (int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++) {
            Sheet sheet = workbook.getSheet(sheetNumber);
            String table = sheet.getName();
            Cell[] row;
            if (sheet.getRows() > 0) {
                listColums = new LinkedList<String>();
                row = sheet.getRow(0);
                for (Cell cell : row) {
                    String content = cell.getContents();
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
                for (String column : listColums) {
//                    queryBegin.append('\'');
                    queryBegin.append(column);
//                    queryBegin.append('\'');
                    queryBegin.append(',');
                }
                queryBegin.deleteCharAt(queryBegin.length() - 1);
                queryBegin.append(")  VALUES (");
                for (int i = 1; i < sheet.getRows(); i++) {
                    StringBuilder query = new StringBuilder(queryBegin);
                    row = sheet.getRow(i);
                    for (Cell cell : row) {
                        query.append('\'');
                        query.append(cell.getContents());
                        query.append("',");
                    }
                    query.deleteCharAt(query.length() - 1);
                    statement.execute(query.toString());
                }
            }
        }
    }
}


