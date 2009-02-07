package airline.dao;

import airline.BaseClass;
import airline.criteria.model.DropTableRequest;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.sql.SQLException;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAODropTableRequestTest extends BaseClass {
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

    @After
    public void tearDown() throws Exception {
        try
        {
            super.tearDown();
        } catch (SQLException e)
        {
            // OKAY
        }
    }


    @Test
    public void testDropTable() {
        airlineDAO.executeRequest(new DropTableRequest(table2));
        Map<String, Table> tables = airlineDAO.getTables();
        assertFalse(tables.containsKey(table2.getName()));
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}