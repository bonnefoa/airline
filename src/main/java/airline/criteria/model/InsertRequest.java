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

import airline.model.TableColumn;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Object request for insert request
 */
public class InsertRequest implements IRequest {

    private Map<TableColumn, String> columnsStringMap;

    public InsertRequest() {
        columnsStringMap = new HashMap<TableColumn, String>();
    }

    public void addNewEntry(TableColumn columns, String value) {
        columnsStringMap.put(columns, value);
    }

    public String buildQuery() {
        Set<TableColumn> keys = columnsStringMap.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Insert INTO ");
        stringBuilder.append(keys.iterator().next().getTable().getName());
        stringBuilder.append("(");
        for (TableColumn columns : keys) {
            stringBuilder.append(columns.getName());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        stringBuilder.append(" VALUES");
        stringBuilder.append("(");
        for (String s : columnsStringMap.values()) {
            stringBuilder.append('\'');
            stringBuilder.append(s);
            stringBuilder.append('\'');
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
