package airline.dao;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import airline.BaseClass;
import airline.model.Tables;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import com.google.inject.Inject;

import java.util.Map;

import junit.framework.Assert;
import static junit.framework.Assert.assertTrue;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOTest extends BaseClass {
    private Connector connector;

    private AirlineDAO airlineDAO;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        connector.initSchema();
        connector.fillTables();
    }

    @After
    public void tearDown() throws Exception {
        connector.dropTables();
    }

    @Test
    public void testGetTablesEntities() {
        Map<String, Tables> tablesEntityMap = airlineDAO.getTablesEntities();
        Assert.assertTrue(tablesEntityMap.size() > 0);
        assertTrue(tablesEntityMap.containsKey(ConnectorTestImpl.TABLE1));
        assertTrue(tablesEntityMap.containsKey(ConnectorTestImpl.TABLE2));
    }

    @Test
    public void testGetTablesColumns() {
        // Add your code here
    }

    @Test
    public void testGetTablesRows() {
        // Add your code here
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
