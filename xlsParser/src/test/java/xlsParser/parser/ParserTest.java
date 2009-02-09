package xlsParser.parser;

import org.junit.Test;
import org.junit.Before;

import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Feb 9, 2009
 * Time: 8:24:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParserTest {
    private Parser parser;

    @Before
    public void setUp() throws IOException, SQLException {
        parser = new Parser("test");
    }

    @Test
    public void testInitTables() throws IOException, SQLException {
        parser.initTables("xlsParser/src/main/resources/script.sql");
    }

    @Test
    public void testParseXls() throws IOException, SQLException, BiffException {
        parser.initTables("xlsParser/src/main/resources/script.sql");
        parser.parseXls("xlsParser/src/main/resources/AirLineData.xls");
    }
}
