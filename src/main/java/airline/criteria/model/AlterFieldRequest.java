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

package airline.criteria.model;

import airline.model.Table;
import airline.model.TableColumn;

import java.util.Map;

/**
 * Request to alter field.
 * Create a sql request to rename, modify or add/delete primary key
 */
public class AlterFieldRequest implements IRequest {

    private Table table;

    private Map<TableColumn, TableColumn> mapColumnses;

    public AlterFieldRequest(Table table, Map<TableColumn, TableColumn> mapColumnses) {
        this.table = table;
        this.mapColumnses = mapColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<TableColumn, TableColumn> tablesColumnsTablesColumnsEntry : mapColumnses.entrySet()) {

            TableColumn oldColumns = tablesColumnsTablesColumnsEntry.getKey();
            TableColumn newColumns = tablesColumnsTablesColumnsEntry.getValue();
            if (oldColumns.isPrimaryKey()
                    != newColumns.isPrimaryKey()) {
                res.append("ALTER TABLE ");
                res.append(table.getName());
                if (newColumns.isPrimaryKey()) {
                    res.append(" ADD PRIMARY KEY (");
                } else {
                    res.append(" DROP PRIMARY KEY (");
                }
                res.append(oldColumns.getName());
                res.append(')');
                res.append(';');
            }

            if (!oldColumns.getName().equals(newColumns.getName())) {
                //ALTER TABLE <tablename> ALTER COLUMN <columnname> RENAME TO <newname> 
                res.append("ALTER TABLE ");
                res.append(table.getName());
                res.append(" ALTER COLUMN ");
                res.append(oldColumns.getName());
                res.append(" RENAME TO ");
                res.append(newColumns.getName());
                res.append(';');
            }

            if (oldColumns.getDataType() != newColumns.getDataType()) {
                //ALTER TABLE <table> ALTER COLUMN <column> <new type>

                res.append("ALTER TABLE ");
                res.append(table.getName());
                res.append(" ALTER COLUMN ");
                res.append(newColumns.getName());
                res.append(' ');
                res.append(newColumns.getSqlStringDataType());
                res.append(';');
            }
        }
        return res.toString();
    }
}