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

import airline.criteria.UpdateSetter;
import airline.criteria.Restriction;
import airline.model.Table;

import java.util.List;
import java.util.ArrayList;

/**
 * Object to create sql update request. 
 */
public class UpdateRequest implements IRequest {

    private Table table;

    private List<UpdateSetter> updateSetterList;

    private List<Restriction> restrictionList;

    public UpdateRequest(Table table) {
        updateSetterList = new ArrayList<UpdateSetter>();
        restrictionList = new ArrayList<Restriction>();
        this.table = table;
    }

    public void addUpdateSetter(UpdateSetter updateSetter) {
        updateSetterList.add(updateSetter);
    }

    public void addRestriction(Restriction restriction) {
        restrictionList.add(restriction);
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("Update ");
        builder.append(table.getName());
        builder.append(" set ");
        for (UpdateSetter updateSetter : updateSetterList) {
            builder.append(updateSetter.toString());
            builder.append(" ,");
        }
        builder.deleteCharAt(builder.length() - 1);
        if (restrictionList.size() > 0) {
            builder.append("where ");
            for (Restriction restriction : restrictionList) {
                builder.append(restriction.toString());
            }
            builder.append(" or");
        }
        if (builder.charAt(builder.length() - 1) == 'r') {
            builder.delete(builder.length() - 3, builder.length());
        }
        return builder.toString();
    }
}
