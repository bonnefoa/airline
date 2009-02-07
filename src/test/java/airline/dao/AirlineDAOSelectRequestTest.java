package airline.dao;

import airline.BaseClass;
import airline.criteria.model.SelectRequest;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.Restriction;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
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
    private AirlineDAO airlineDAO;
    private Table table1;
    private Table table2;
    private String[][] values = new String[4][2];
    private List<TablesColumns> listColumns2;
    private List<TablesColumns> listColumns1;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        table1 = tablesEntityMap.get(ConnectorTestImpl.TABLE1);
        table2 = tablesEntityMap.get(ConnectorTestImpl.TABLE2);
        values[0] = new String[]{"1", "name", "message", "2009-01-01 12:00:00.0"};
        values[1] = new String[]{"2", "name2", "message2", "2009-01-01 12:00:01.0"};
        values[2] = new String[]{"3", "name3", "message3", "2009-01-01 12:00:03.0"};
        values[3] = new String[]{"4", "name4", "message4", "2009-01-01 12:00:04.0"};
        listColumns2 = airlineDAO.getTablesColumns(table2);
        listColumns1 = airlineDAO.getTablesColumns(table1);
    }

    @Test
    public void testGetSelectRequest() {
        SelectRequest selectRequest = new SelectRequest();
        selectRequest.addColumn(listColumns2.get(0));
        selectRequest.addColumn(listColumns2.get(1));
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(4, result.size());

        List<TableRow> tableRows = airlineDAO.getTablesRows(table2);

        for (int numRow = 0; numRow < tableRows.size(); numRow++)
        {
            TableRow row = tableRows.get(numRow);
            int i = 0;
            for (String s : row.values())
            {
                assertEquals(values[numRow][i++], s);
            }
        }
    }

    @Test
    public void testSelectTwoTables() {
        SelectRequest selectRequest = new SelectRequest();
        selectRequest.addColumn(listColumns1.get(0));
        selectRequest.addColumn(listColumns1.get(1));
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
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(1, result.size());
        TableRow row = result.iterator().next();
        assertEquals("1", row.get(listColumns1.get(0)));
        assertEquals("present", row.get(listColumns1.get(1)));
        assertEquals("1", row.get(listColumns2.get(0)));
        assertEquals("name", row.get(listColumns2.get(1)));
    }

    @Test
    public void testSelectWithRestriction() {
        SelectRequest selectRequest = new SelectRequest();
        selectRequest.addColumn(listColumns2.get(0));
        selectRequest.addColumn(listColumns2.get(1));
        selectRequest.addColumn(listColumns2.get(2));
        Restriction restriction = new Restriction();
        restriction.constraint(
                listColumns2.get(0), "2",
                SqlConstraints.GT);
        selectRequest.addRestriction(restriction);
        System.out.println(selectRequest.buildQuery());
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(2, result.size());
    }


    @Test
    public void testSelectWithTwoRestrictionsAnd() {
        SelectRequest selectRequest = new SelectRequest();
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
        res3.and(restriction1, restriction2);
        selectRequest.addRestriction(res3);
        System.out.println(selectRequest.buildQuery());
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(1, result.size());
    }


    @Test
    public void testSelectWithTwoRestrictionsOr() {
        SelectRequest selectRequest = new SelectRequest();
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
        res3.or(restriction1, restriction2);
        selectRequest.addRestriction(res3);
        System.out.println(selectRequest.buildQuery());
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(4, result.size());
    }

    @Test
    public void testSelectWithoutColums() {
        SelectRequest selectRequest = new SelectRequest(table2);
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(4, result.size());
        int numRow = 0;
        for (TableRow row : result)
        {
            assertEquals(4, row.keySet().size());
            int i = 0;
            for (String s : row.values())
            {
                assertEquals(values[numRow][i++], s);
            }
            numRow++;
        }
    }

    @Test
    public void testSelectWithoutColumsAndWithRestrictions() {
        Restriction restriction = new Restriction();
        restriction.constraint(
                listColumns2.get(0), "2",
                SqlConstraints.GT);
        SelectRequest selectRequest = new SelectRequest(table2,restriction);
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        assertEquals(2, result.size());
        int numRow = 2;
        for (TableRow row : result)
        {
            assertEquals(4, row.keySet().size());
            int i = 0;
            for (String s : row.values())
            {
                assertEquals(values[numRow][i++], s);
            }
            numRow++;
        }
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}