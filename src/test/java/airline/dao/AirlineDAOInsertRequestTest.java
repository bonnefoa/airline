package airline.dao;

import airline.BaseClass;
import airline.criteria.model.InsertRequest;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TableColumn;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAOInsertRequestTest extends BaseClass {
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
    public void testInsertRequest() {
        InsertRequest insertRequest = new InsertRequest();
        List<TableColumn> columnsList = airlineDAO.getTableColumns(table2);

        insertRequest.addNewEntry(columnsList.get(1), "Ggorau");
        insertRequest.addNewEntry(columnsList.get(2), "messaged");


    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}