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
import airline.criteria.Restriction;
import airline.criteria.UpdateSetter;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.model.SelectRequest;
import airline.criteria.model.UpdateRequest;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TableColumn;
import com.google.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Test for updateRequest
 */
public class AirlineDAOUpdateRequestTest extends BaseClass {
    private AirlineDAO airlineDAO;
    private Table table2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Map<String, Table> tablesEntityMap = airlineDAO.getTables();
        table2 = tablesEntityMap.get(ConnectorTestImpl.TABLE2);
    }

    @Test
    public void testUpdateRequest() throws SQLException {
        UpdateRequest updateRequest = new UpdateRequest(table2);
        List<TableColumn> listColumns = airlineDAO.getTableColumns(table2);
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
        Set<TableRow> result = airlineDAO.executeRequest(request);
        assertEquals(1, result.size());
        TableRow row = result.iterator().next();
        assertEquals("1", row.get(listColumns.get(0)));
        assertEquals("newName", row.get(listColumns.get(1)));
    }


    @Test
    public void testUpdateRequestMultiple() throws SQLException {
        UpdateRequest updateRequest = new UpdateRequest(table2);
        List<TableColumn> listColumns = airlineDAO.getTableColumns(table2);
        updateRequest.addUpdateSetter(new UpdateSetter(listColumns.get(1), "newName"));
        airlineDAO.executeRequest(updateRequest);

        Restriction restriction = new Restriction();
        restriction.constraint(listColumns.get(1), "newName", SqlConstraints.EQ);
        SelectRequest request = new SelectRequest();
        request.addColumn(listColumns.get(0));
        request.addColumn(listColumns.get(1));
        request.addRestriction(restriction);
        Set<TableRow> result = airlineDAO.executeRequest(request);
        assertEquals(4, result.size());
        for (TableRow tableRow : result) {
            assertEquals("newName", tableRow.get(listColumns.get(1)));
        }
    }


    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }
}