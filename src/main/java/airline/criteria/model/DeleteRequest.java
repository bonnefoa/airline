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

import airline.criteria.Restriction;
import airline.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Object for deleteRequest
 */
public class DeleteRequest implements IRequest {

    private Table table;

    private List<Restriction> restrictionList;

    public void addRestriction(Restriction restriction) {
        restrictionList.add(restriction);
    }

    public DeleteRequest(Table table) {
        this.table = table;
        restrictionList = new ArrayList<Restriction>();
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("DELETE FROM ");
        res.append(table.getName());
        res.append(' ');
        if (restrictionList.size() > 0) {
            res.append("WHERE ");
            for (Restriction restriction : restrictionList) {
                res.append(restriction.toString());
            }
        }
        return res.toString();
    }
}
