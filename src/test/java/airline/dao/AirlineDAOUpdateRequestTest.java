package airline.dao;

import airline.BaseClass;
import airline.criteria.impl.SelectRequest;
import airline.criteria.impl.UpdateRequest;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.Restriction;
import airline.criteria.UpdateSetter;
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
 * Test for updateRequest
 */
public class AirlineDAOUpdateRequestTest extends BaseClass {

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
    public void testUpdateRequest() {
        UpdateRequest updateRequest = new UpdateRequest(table2);
        List<TablesColumns> listColumns = airlineDAO.getTablesColumns(table2);
        updateRequest.addUpdateSetter(new UpdateSetter(listColumns.get(1), "newName"));
        Restriction restriction = new Restriction();
        restriction.constraint(listColumns.get(0), "1", SqlConstraints.EQ);
        updateRequest.addRestriction(restriction);
        airlineDAO.executeRequest(updateRequest);

        restriction = new Restriction();
        restriction.constraint(listColumns.get(1), "newName", SqlConstraints.EQ);
        SelectRequest request = new SelectRequest();
        request.addColumn(listColumns.get(0));
        request.addColumn(listColumns.get(1));
        request.addRestriction(restriction);
        Set<TablesRow> result = airlineDAO.executeRequest(request);
        assertEquals(1,result.size());
        TablesRow row = result.iterator().next();
        assertEquals("1",row.get(listColumns.get(0)));
        assertEquals("newName",row.get(listColumns.get(1)));
    }


    @Test
    public void testUpdateRequestMultiple() {
        UpdateRequest updateRequest = new UpdateRequest(table2);
        List<TablesColumns> listColumns = airlineDAO.getTablesColumns(table2);
        updateRequest.addUpdateSetter(new UpdateSetter(listColumns.get(1), "newName"));
        airlineDAO.executeRequest(updateRequest);
        
        Restriction restriction = new Restriction();
        restriction.constraint(listColumns.get(1), "newName", SqlConstraints.EQ);
        SelectRequest request = new SelectRequest();
        request.addColumn(listColumns.get(0));
        request.addColumn(listColumns.get(1));
        request.addRestriction(restriction);
        Set<TablesRow> result = airlineDAO.executeRequest(request);
        assertEquals(4,result.size());
        for (TablesRow tablesRow : result) {
            assertEquals("newName",tablesRow.get(listColumns.get(1)));
        }
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