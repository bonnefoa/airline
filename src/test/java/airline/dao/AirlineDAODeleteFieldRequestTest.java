package airline.dao;

import airline.BaseClass;
import airline.connector.impl.ConnectorTestImpl;
import airline.criteria.model.DeleteFieldRequest;
import airline.model.Table;
import airline.model.TablesColumns;
import com.google.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAODeleteFieldRequestTest extends BaseClass {

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
    public void testDeleteFieldTable() throws SQLException {
        List<TablesColumns> columnsList=airlineDAO.getTablesColumns(table2);
        DeleteFieldRequest deleteField = new DeleteFieldRequest(table2,columnsList.subList(0,2));
        airlineDAO.executeRequest(deleteField);
        columnsList=airlineDAO.getTablesColumns(table2);
        assertEquals(2,columnsList.size());
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}