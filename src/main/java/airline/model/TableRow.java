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

package airline.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entities row.
 * Map each TableColumn with a String representing the
 * result in database. Since we want to conserve the order, we
 * extends LinkedHashMap
 */
public class TableRow extends LinkedHashMap<TableColumn, String> {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<TableColumn, String> tablesColumnsStringEntry : this.entrySet()) {
            builder.append(tablesColumnsStringEntry.getKey());
            builder.append(' ');
            builder.append(':');
            builder.append(' ');
            builder.append(tablesColumnsStringEntry.getValue());
            builder.append(' ');
        }
        return builder.toString();
    }
}
