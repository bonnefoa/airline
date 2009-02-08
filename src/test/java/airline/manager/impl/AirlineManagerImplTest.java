package airline.manager.impl;

import airline.BaseClass;
import airline.connector.impl.ConnectorTestImpl;
import airline.criteria.model.DeleteRequest;
import airline.manager.AirlineManager;
import airline.model.Table;
import airline.model.Transaction;
import com.google.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

/**
 * User: sora
 * Date: 7 févr. 2009
 * Time: 16:29:10
 */
public class AirlineManagerImplTest extends BaseClass {

    private AirlineManager manager;
    private AirlineManagerImpl airlineDAO;
    private Table table2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        table2 = tablesEntityMap.get(ConnectorTestImpl.TABLE2);
    }

    @Test
    public void testExecuteRequest() throws SQLException {
        DeleteRequest request = new DeleteRequest(table2);
        manager.executeRequest(request);
        List<Transaction> list = manager.getTransactions();
        assertEquals(1, list.size());
        assertEquals(request.buildQuery(), list.get(0).getDescription());
    }

    @Inject
    public void setManager(AirlineManager manager) {
        this.manager = manager;
    }

    @Inject
    public void setAirlineDAO(AirlineManagerImpl airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}
