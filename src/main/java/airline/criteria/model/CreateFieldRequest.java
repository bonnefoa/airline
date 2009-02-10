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

import java.util.List;

/**
 * Request to create field
 */
public class CreateFieldRequest extends Request {

    private Table table;

    private List<TableColumn> tablesColumnses;

    public CreateFieldRequest(Table table, List<TableColumn> tablesColumnses) {
        this.table = table;
        this.tablesColumnses = tablesColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        for (TableColumn tablesColumnse : tablesColumnses) {
            res.append("ALTER TABLE ");
            res.append(table.getName());
            res.append(" ADD ");
            res.append(tablesColumnse.getName());
            res.append(' ');
            res.append(tablesColumnse.getSqlStringDataType());
            res.append(';');
        }
        return res.toString();
    }
}