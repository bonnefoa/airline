package airline.dao;

import airline.BaseClass;
import airline.criteria.model.CreateFieldRequest;
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
import java.util.ArrayList;
import java.sql.Types;
import java.sql.SQLException;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOCreateFieldRequestTest extends BaseClass {
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
    public void testCreateFieldTable() {
        List<TablesColumns> columnsList = new ArrayList<TablesColumns>();
        TablesColumns col = new TablesColumns();
        col.setName("GRA");
        col.setDataType(Types.INTEGER);
        col.setPrimaryKey(true);
        columnsList.add(col);
        CreateFieldRequest createFieldRequest = new CreateFieldRequest(table2, columnsList);
        airlineDAO.executeRequest(createFieldRequest);
        columnsList = airlineDAO.getTablesColumns(table2);
        assertEquals(5, columnsList.size());
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