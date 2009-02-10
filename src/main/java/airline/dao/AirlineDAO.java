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

import airline.model.Table;
import airline.model.TableColumn;
import airline.model.TableRow;
import airline.criteria.model.SelectRequest;
import airline.criteria.model.IRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.SQLException;

/**
 * DAO interface for the dao
 */
public interface AirlineDAO {

    /**
     * Get the map between the tables name and the tables entity
     *
     * @return Map of tables name and the tables entity
     */
    Map<String, Table> getTables();

    /**
     * Get the list of tablesColumns
     *
     * @param table table to identify
     * @return Map of columns name and the tables Columns
     */
    List<TableColumn> getTableColumns(Table table);

    /**
     * Get all the rows of the given table
     *
     * @param table Table to search
     * @return List of tableRows
     */
    List<TableRow> getTablesRows(Table table);

    /**
     * Execute the given select request
     *
     * @param selectRequest Select request to execute
     * @return Set of tableRows
     * @throws java.sql.SQLException If the request gone wrong
     */
    Set<TableRow> executeRequest(SelectRequest selectRequest) throws SQLException;

    /**
     * Execute the given request
     *
     * @param request Request to execute
     * @throws java.sql.SQLException If the request gone wrong
     */
    void executeRequest(IRequest request) throws SQLException;
}
