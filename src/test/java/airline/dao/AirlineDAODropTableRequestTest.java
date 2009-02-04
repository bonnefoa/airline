package airline.dao;

import airline.BaseClass;
import airline.criteria.impl.SelectRequest;
import airline.criteria.impl.CreateTableRequest;
import airline.criteria.impl.DropTableRequest;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.Restriction;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
import junit.framework.Assert;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.sql.Types;
import java.sql.SQLException;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:02:21
 */
public class AirlineDAODropTableRequestTest extends BaseClass {
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
    public void tearDown(){
        try {
            connector.dropTables();
        } catch (SQLException e) {
            // OKAU
        }
    }


    @Test
    public void testDropTable() {
        airlineDAO.executeRequest(new DropTableRequest(table2));
        Map<String, Table> tables = airlineDAO.getTables();
        assertFalse(tables.containsKey(table2.getName()));
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