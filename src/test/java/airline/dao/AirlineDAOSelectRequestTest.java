package airline.dao;

import airline.BaseClass;
import airline.criteria.impl.SelectRequest;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.Restriction;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOSelectRequestTest extends BaseClass {
    private Connector connector;

    private AirlineDAO airlineDAO;
    private Table table1;
    private Table table2;
    private String[][] values = new String[4][2];

    @Before
    public void setUp() throws Exception {
        super.setUp();
        connector.initSchema();
        connector.fillTables();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        table1 = tablesEntityMap.get(ConnectorTestImpl.TABLE1);
        table2 = tablesEntityMap.get(ConnectorTestImpl.TABLE2);
        values[0] = new String[]{"1", "name", "message", "2009-01-01 12:00:00.0"};
        values[1] = new String[]{"2", "name2", "message2", "2009-01-01 12:00:01.0"};
        values[2] = new String[]{"3", "name3", "message3", "2009-01-01 12:00:03.0"};
        values[3] = new String[]{"4", "name4", "message4", "2009-01-01 12:00:04.0"};
    }

    @After
    public void tearDown() throws Exception {
        connector.dropTables();
    }

    @Test
    public void testGetSelectRequest() {
        SelectRequest selectRequest = new SelectRequest();
        List<TablesColumns> listColumns = airlineDAO.getTablesColumns(table2);
        selectRequest.addColumn(listColumns.get(0));
        selectRequest.addColumn(listColumns.get(1));
        Set<TablesRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(4, result.size());

        List<TablesRow> tablesRows = airlineDAO.getTablesRows(table2);

        for (int numRow = 0; numRow < tablesRows.size(); numRow++) {
            TablesRow row = tablesRows.get(numRow);
            int i = 0;
            for (String s : row.values()) {
                assertEquals(values[numRow][i++], s);
            }
        }
    }

    @Test
    public void testSelectTwoTables() {
        SelectRequest selectRequest = new SelectRequest();
        List<TablesColumns> listColumns1 = airlineDAO.getTablesColumns(table1);
        selectRequest.addColumn(listColumns1.get(0));
        selectRequest.addColumn(listColumns1.get(1));
        List<TablesColumns> listColumns2 = airlineDAO.getTablesColumns(table2);
        selectRequest.addColumn(listColumns2.get(0));
        selectRequest.addColumn(listColumns2.get(1));
        selectRequest.addColumn(listColumns2.get(2));
        Restriction restriction = new Restriction();
        restriction.constraint(
                listColumns2.get(0),
                listColumns1.get(0),
                SqlConstraints.EQ);
        selectRequest.addRestriction(restriction);
        System.out.println(selectRequest.buildQuery());
        Set<TablesRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(1, result.size());
        TablesRow row = result.iterator().next();
        assertEquals("1", row.get(listColumns1.get(0)));
        assertEquals("present", row.get(listColumns1.get(1)));
        assertEquals("1", row.get(listColumns2.get(0)));
        assertEquals("name", row.get(listColumns2.get(1)));
    }

    @Test
    public void testSelectWithRestriction() {
        SelectRequest selectRequest = new SelectRequest();
        List<TablesColumns> listColumns2 = airlineDAO.getTablesColumns(table2);
        selectRequest.addColumn(listColumns2.get(0));
        selectRequest.addColumn(listColumns2.get(1));
        selectRequest.addColumn(listColumns2.get(2));
        Restriction restriction = new Restriction();
        restriction.constraint(
                listColumns2.get(0), "2",
                SqlConstraints.GT);
        selectRequest.addRestriction(restriction);
        System.out.println(selectRequest.buildQuery());
        Set<TablesRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(2, result.size());
    }


    @Test
    public void testSelectWithTwoRestrictionsAnd() {
        SelectRequest selectRequest = new SelectRequest();
        List<TablesColumns> listColumns2 = airlineDAO.getTablesColumns(table2);
        selectRequest.addColumn(listColumns2.get(0));
        selectRequest.addColumn(listColumns2.get(1));
        selectRequest.addColumn(listColumns2.get(2));
        Restriction restriction1 = new Restriction();
        Restriction restriction2 = new Restriction();
        restriction1.constraint(
                listColumns2.get(0), "2",
                SqlConstraints.GT);
        restriction2.constraint(
                listColumns2.get(0), "4",
                SqlConstraints.LT);
        Restriction res3 = new Restriction();
        res3.and(restriction1,restriction2);
        selectRequest.addRestriction(res3);
        System.out.println(selectRequest.buildQuery());
        Set<TablesRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(1, result.size());
    }


    @Test
    public void testSelectWithTwoRestrictionsOr() {
        SelectRequest selectRequest = new SelectRequest();
        List<TablesColumns> listColumns2 = airlineDAO.getTablesColumns(table2);
        selectRequest.addColumn(listColumns2.get(0));
        selectRequest.addColumn(listColumns2.get(1));
        selectRequest.addColumn(listColumns2.get(2));
        Restriction restriction1 = new Restriction();
        Restriction restriction2 = new Restriction();
        restriction1.constraint(
                listColumns2.get(0), "2",
                SqlConstraints.GT);
        restriction2.constraint(
                listColumns2.get(0), "4",
                SqlConstraints.LT);
        Restriction res3 = new Restriction();
        res3.or(restriction1,restriction2);
        selectRequest.addRestriction(res3);
        System.out.println(selectRequest.buildQuery());
        Set<TablesRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(4, result.size());
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