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
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.model.DeleteRequest;
import airline.criteria.model.SelectRequest;
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
    public void testDeleteRequest() throws SQLException {
        SelectRequest select;
        select = new SelectRequest(table2);
        airlineDAO.executeRequest(select);
        DeleteRequest deleteRequest = new DeleteRequest(table2);
        List<TableColumn> columnsList = airlineDAO.getTableColumns(table2);
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
        for (TableRow re : res) {
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