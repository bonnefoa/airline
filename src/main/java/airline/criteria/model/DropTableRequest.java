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

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:36:01
 */
public class DropTableRequest extends Request {
    private Table table;

    public DropTableRequest(Table table) {
        this.table = table;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("DROP TABLE ");
        res.append(table.getName());
        return res.toString();
    }
}
