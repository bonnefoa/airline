package xlsParser.parser;

import jxl.read.biff.BiffException;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.net.URISyntaxException;

/**
 * Test of the handler
 */
public class HandlerTest {
    Handler handler;

    @Test
    public void testExecute() throws BiffException, IOException, SQLException, URISyntaxException {
        handler = new Handler("test",
                "src/test/resources/script.sql",
                "src/test/resources/AirLineData.xls");
        handler.execute();
    }
}
