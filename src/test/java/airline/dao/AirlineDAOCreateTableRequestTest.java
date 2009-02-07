package airline.dao;

import airline.BaseClass;
import airline.criteria.model.CreateTableRequest;
import airline.criteria.model.DropTableRequest;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.sql.Types;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOCreateTableRequestTest extends BaseClass {
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
    public void testCreateTable() {
        Table table = new Table("UGUU");
        List<TablesColumns> columnsList = new ArrayList<TablesColumns>();
        TablesColumns columns = new TablesColumns();
        columns.setName("GRAOU");
        columns.setDataType(Types.INTEGER);
        columnsList.add(columns);
        airlineDAO.executeRequest(new CreateTableRequest(table, columnsList));

        Map<String, Table> listTables = airlineDAO.getTables();
        assertTrue(listTables.containsKey("UGUU"));
        columnsList = airlineDAO.getTablesColumns(listTables.get("UGUU"));
        assertEquals("GRAOU", columnsList.get(0).getName());
        assertEquals("INTEGER", columnsList.get(0).getType());
    }

    @Test
    public void testCreateTableTwoColumns() {
        Map<String, Table> listTables;
        Table table = new Table("UGUU");
        List<TablesColumns> columnsList = new ArrayList<TablesColumns>();
        TablesColumns columns = new TablesColumns();
        columns.setName("GRAOU");
        columns.setDataType(Types.INTEGER);
        columnsList.add(columns);

        columns = new TablesColumns();
        columns.setName("GRAOUPU");
        columns.setDataType(Types.VARCHAR);
        columnsList.add(columns);

        listTables = airlineDAO.getTables();
        if (listTables.containsKey("UGUU")) {
            airlineDAO.executeRequest(new DropTableRequest(listTables.get("UGUU")));
        }

        airlineDAO.executeRequest(new CreateTableRequest(table, columnsList));

        listTables = airlineDAO.getTables();
        assertTrue(listTables.containsKey("UGUU"));
        columnsList = airlineDAO.getTablesColumns(listTables.get("UGUU"));
        assertEquals("GRAOU", columnsList.get(0).getName());
        assertEquals("INTEGER", columnsList.get(0).getType());
        assertEquals("GRAOUPU", columnsList.get(1).getName());
        assertEquals("VARCHAR", columnsList.get(1).getType());
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