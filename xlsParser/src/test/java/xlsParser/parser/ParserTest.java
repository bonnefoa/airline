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
