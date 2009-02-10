/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package airline.dao;

import airline.BaseClass;
import airline.connector.impl.ConnectorTestImpl;
import airline.criteria.model.CreateTableRequest;
import airline.criteria.model.DropTableRequest;
import airline.model.Table;
import airline.model.TableColumn;
import com.google.inject.Inject;
import static junit.framework.Assert.assertTrue;
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
public class AirlineDAOCreateTableRequestTest extends BaseClass {

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
    public void testCreateTable() throws SQLException {
        Table table = new Table("UGUU");
        List<TableColumn> columnsList = new ArrayList<TableColumn>();
        TableColumn columns = new TableColumn();
        columns.setName("GRAOU");
        columns.setDataType(Types.INTEGER);
        columnsList.add(columns);
        airlineDAO.executeRequest(new CreateTableRequest(table, columnsList));

        Map<String, Table> listTables = airlineDAO.getTables();
        assertTrue(listTables.containsKey("UGUU"));
        columnsList = airlineDAO.getTableColumns(listTables.get("UGUU"));
        assertEquals("GRAOU", columnsList.get(0).getName());
        assertEquals("INTEGER", columnsList.get(0).getType());
        assertEquals(Types.INTEGER, columnsList.get(0).getDataType());
    }

    @Test
    public void testCreateTableTwoColumns() throws SQLException {
        Map<String, Table> listTables;
        Table table = new Table("UGUU");
        List<TableColumn> columnsList = new ArrayList<TableColumn>();
        TableColumn columns = new TableColumn();
        columns.setName("GRAOU");
        columns.setDataType(Types.INTEGER);
        columnsList.add(columns);

        columns = new TableColumn();
        columns.setName("GRAOUPU");
        columns.setDataType(Types.VARCHAR);
        columnsList.add(columns);

        listTables = airlineDAO.getTables();
        if (listTables.containsKey("UGUU")) {
            airlineDAO.executeRequest(new DropTableRequest(listTables.get("UGUU")));
        }

        airlineDAO.executeRequest(new CreateTableRequest(table, columnsList));

        listTables = airlineDAO.getTables();
        assertTrue(listTables.containsKey("UGUU"));
        columnsList = airlineDAO.getTableColumns(listTables.get("UGUU"));
        assertEquals("GRAOU", columnsList.get(0).getName());
        assertEquals("INTEGER", columnsList.get(0).getType());
        assertEquals(Types.INTEGER, columnsList.get(0).getDataType());
        assertEquals("GRAOUPU", columnsList.get(1).getName());
        assertEquals("VARCHAR", columnsList.get(1).getType());
        assertEquals(Types.VARCHAR, columnsList.get(1).getDataType());
    }

    @Test
    public void testCreateTableTwoColumnsPrimary() throws SQLException {
        Map<String, Table> listTables;
        Table table = new Table("UGUU");
        List<TableColumn> columnsList = new ArrayList<TableColumn>();
        TableColumn columns = new TableColumn();
        columns.setName("GRAOU");
        columns.setDataType(Types.INTEGER);
        columns.setPrimaryKey(true);
        columnsList.add(columns);

        columns = new TableColumn();
        columns.setName("GRAOUPU");
        columns.setDataType(Types.VARCHAR);
        columnsList.add(columns);

        listTables = airlineDAO.getTables();
        if (listTables.containsKey("UGUU")) {
            airlineDAO.executeRequest(new DropTableRequest(listTables.get("UGUU")));
        }

        airlineDAO.executeRequest(new CreateTableRequest(table, columnsList));

        listTables = airlineDAO.getTables();
        assertTrue(listTables.containsKey("UGUU"));
        columnsList = airlineDAO.getTableColumns(listTables.get("UGUU"));
        assertEquals("GRAOU", columnsList.get(0).getName());
        assertEquals("INTEGER", columnsList.get(0).getType());
        assertEquals(Types.INTEGER, columnsList.get(0).getDataType());
        assertTrue(columnsList.get(0).isPrimaryKey());
        assertEquals("GRAOUPU", columnsList.get(1).getName());
        assertEquals("VARCHAR", columnsList.get(1).getType());
        assertEquals(Types.VARCHAR, columnsList.get(1).getDataType());
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}