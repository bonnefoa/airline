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
    private Table table2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
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