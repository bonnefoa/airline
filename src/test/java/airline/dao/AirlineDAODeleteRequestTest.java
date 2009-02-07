package airline.dao;

import airline.BaseClass;
import airline.criteria.model.SelectRequest;
import airline.criteria.model.DeleteRequest;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.Restriction;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
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
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAODeleteRequestTest extends BaseClass {
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
    public void testDeleteRequest() {
        SelectRequest select;
        select = new SelectRequest(table2);
        airlineDAO.executeRequest(select);
        DeleteRequest deleteRequest = new DeleteRequest(table2);
        List<TablesColumns> columnsList = airlineDAO.getTablesColumns(table2);
        Restriction restriction = new Restriction();
        restriction.constraint(columnsList.get(0), "1", SqlConstraints.EQ);
        deleteRequest.addRestriction(restriction);
        airlineDAO.executeRequest(deleteRequest);

        select = new SelectRequest(table2, restriction);
        Set<TableRow> res = airlineDAO.executeRequest(select);
        assertEquals(0, res.size());

        select = new SelectRequest(table2);

        res = airlineDAO.executeRequest(select);
        assertEquals(3, res.size());
        int i = 2;
        for (TableRow re : res)
        {
            System.out.println(re.toString());
            System.out.println(columnsList.get(0));
            assertEquals(i + "", re.get(columnsList.get(0)));
            i++;
        }
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}