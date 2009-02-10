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
        parser = new ParserImpl("test");
    }

    @Test
    public void testInitTables() throws IOException, SQLException {
        parser.initTables("src/test/resources/script.sql");
    }

    @Test
    public void testParseXls() throws IOException, SQLException, BiffException {
        parser.initTables("src/test/resources/script.sql");
        parser.parseXls("src/test/resources/AirLineData.xls");
    }
}
