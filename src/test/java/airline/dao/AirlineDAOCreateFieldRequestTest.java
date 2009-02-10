package airline.dao;

import airline.BaseClass;
import airline.connector.impl.ConnectorTestImpl;
import airline.criteria.model.CreateFieldRequest;
import airline.model.Table;
import airline.model.TableColumn;
import com.google.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.sql.Types;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOCreateFieldRequestTest extends BaseClass {
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
    public void testCreateFieldTable() throws SQLException {
        List<TableColumn> columnsList = new ArrayList<TableColumn>();
        TableColumn col = new TableColumn();
        col.setName("GRA");
        col.setDataType(Types.INTEGER);
        col.setPrimaryKey(true);
        columnsList.add(col);
        CreateFieldRequest createFieldRequest = new CreateFieldRequest(table2, columnsList);
        airlineDAO.executeRequest(createFieldRequest);
        columnsList = airlineDAO.getTableColumns(table2);
        assertEquals(5, columnsList.size());
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}