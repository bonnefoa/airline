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

package airline.criteria;

import airline.model.TableColumn;

/**
 * Setter update
 */
public class UpdateSetter {
    private TableColumn tableColumn;

    private String value;

    public UpdateSetter(TableColumn tableColumn, String value) {
        this.tableColumn = tableColumn;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(tableColumn.getTable().getName());
        res.append(".");
        res.append(tableColumn.getName());
        res.append("='");
        res.append(value);
        res.append("' ");
        return res.toString();
    }
}
