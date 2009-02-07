package airline.dao;

import airline.BaseClass;
import airline.criteria.model.DeleteFieldRequest;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAODeleteFieldRequestTest extends BaseClass {
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
    public void tearDown() throws SQLException {
        connector.dropTables();
    }


    @Test
    public void testDeleteFieldTable() {
        List<TablesColumns> columnsList=airlineDAO.getTablesColumns(table2);
        DeleteFieldRequest deleteField = new DeleteFieldRequest(table2,columnsList.subList(0,2));
        airlineDAO.executeRequest(deleteField);
        columnsList=airlineDAO.getTablesColumns(table2);
        assertEquals(2,columnsList.size());
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