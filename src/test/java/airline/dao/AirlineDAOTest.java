package airline.dao;

import airline.BaseClass;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;
import com.google.inject.Inject;
import junit.framework.Assert;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOTest extends BaseClass {
    private Connector connector;

    private AirlineDAO airlineDAO;
    private Table table1;
    private Table table2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        connector.initSchema();
        connector.fillTables();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        table1 = tablesEntityMap.get(ConnectorTestImpl.TABLE1);
        table2 = tablesEntityMap.get(ConnectorTestImpl.TABLE2);
    }

    @After
    public void tearDown() throws Exception {
        connector.dropTables();
    }

    @Test
    public void testGetTablesEntities() {
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        Assert.assertTrue(tablesEntityMap.size() > 0);
        assertTrue(tablesEntityMap.containsKey(ConnectorTestImpl.TABLE1));
        assertTrue(tablesEntityMap.containsKey(ConnectorTestImpl.TABLE2));
    }

    @Test
    public void testGetTablesColumns() {
        Map<String, TablesColumns> cols = airlineDAO.getTablesColumns(table1);
        assertEquals(3, cols.size());
        String[] names = {ConnectorTestImpl.IDENTIFIANT,
                ConnectorTestImpl.NAME,
                ConnectorTestImpl.TIME};
        int i = 0;
        for (TablesColumns tablesColumns : cols.values()) {
            assertEquals(names[i++], tablesColumns.getName());
        }
    }

    @Test
    public void testGetTablesRows() {
        List<TablesRow> tablesRows = airlineDAO.getTablesRows(table2);
        String[][] values = new String[2][2];
        values[0] = new String[]{"1","name", "message", "2009-01-01 12:00:00.0"};
        values[1] = new String[]{"2","name2", "message2", "2009-01-01 12:00:01.0"};

        assertEquals(2, tablesRows.size());
        for (int numRow = 0; numRow < 2; numRow++) {
            TablesRow row = tablesRows.get(numRow);
            int i = 0;
            for (String s : row.values()) {
                assertEquals(values[numRow][i++], s);
            }
        }
    }

    @Inject
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}
