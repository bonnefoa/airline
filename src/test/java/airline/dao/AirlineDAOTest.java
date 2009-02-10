package airline.dao;

import airline.BaseClass;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TableColumn;
import com.google.inject.Inject;
import junit.framework.Assert;
import static junit.framework.Assert.assertTrue;
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
    private AirlineDAO airlineDAO;
    private Table table1;
    private Table table2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        table1 = tablesEntityMap.get(ConnectorTestImpl.TABLE1);
        table2 = tablesEntityMap.get(ConnectorTestImpl.TABLE2);
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
        List<TableColumn> cols = airlineDAO.getTableColumns(table1);
        assertEquals(3, cols.size());
        String[] names = {ConnectorTestImpl.IDENTIFIANT,
                ConnectorTestImpl.NAME,
                ConnectorTestImpl.TIME};
        int i = 0;
        for (TableColumn tableColumn : cols) {
            assertEquals(names[i++], tableColumn.getName());
        }
    }

    @Test
    public void testGetTablesRows() {
        List<TableRow> tableRows = airlineDAO.getTablesRows(table2);
        String[][] values = new String[4][2];
        values[0] = new String[]{"1", "name", "message", "2009-01-01 12:00:00.0"};
        values[1] = new String[]{"2", "name2", "message2", "2009-01-01 12:00:01.0"};
        values[2] = new String[]{"3", "name3", "message3", "2009-01-01 12:00:03.0"};
        values[3] = new String[]{"4", "name4", "message4", "2009-01-01 12:00:04.0"};

        assertEquals(4, tableRows.size());
        for (int numRow = 0; numRow < tableRows.size(); numRow++) {
            TableRow row = tableRows.get(numRow);
            int i = 0;
            for (String s : row.values()) {
                assertEquals(values[numRow][i++], s);
            }
        }
    }


    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}
