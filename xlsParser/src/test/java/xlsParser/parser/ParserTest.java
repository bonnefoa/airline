package xlsParser.parser;

import jxl.read.biff.BiffException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Test of parser
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
