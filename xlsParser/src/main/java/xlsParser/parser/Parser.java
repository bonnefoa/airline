package xlsParser.parser;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.DateFormat;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
                    System.out.println(query.toString());
                    statement.execute(query.toString());
                }
            }
        }
    }
}
