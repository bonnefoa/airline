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
import airline.model.Table;
import airline.criteria.Restriction;

import java.util.*;

/**
 * Object for select request.
 * Take a list of columns or a table. In the last case, it will take all columns of the table.
 * Restrictions are used to construct the where conditions.
 */
public class SelectRequest extends Request {
    private List<TableColumn> columnList;
    private List<Restriction> restrictionList;
    private Set<String> setTables;
    private Table table;

    public SelectRequest() {
        columnList = new LinkedList<TableColumn>();
        restrictionList = new LinkedList<Restriction>();
        setTables = new HashSet<String>();
    }


    public SelectRequest(List<TableColumn> columnList, List<Restriction> restrictionList) {
        this.columnList = columnList;
        this.restrictionList = restrictionList;
        setTables = new HashSet<String>();
        for (Restriction restriction : restrictionList) {
            setTables.addAll(restriction.getSetTables());
        }
        for (TableColumn columns : columnList) {
            setTables.add(columns.getTable().getName());
        }
    }

    public SelectRequest(Table table, List<Restriction> restrictionList) {
        this.table = table;
        this.restrictionList = restrictionList;
        setTables = new HashSet<String>();
        columnList = new LinkedList<TableColumn>();
        for (Restriction restriction : restrictionList) {
            setTables.addAll(restriction.getSetTables());
        }
    }

    public SelectRequest(Table table2, Restriction restriction) {
        this(table2, new ArrayList<Restriction>());
        this.addRestriction(restriction);
    }

    public SelectRequest(Table table2) {
        this();
        this.table = table2;
    }

    public void addColumn(TableColumn column) {
        columnList.add(column);
        setTables.add(column.getTable().getName());
    }

    public void addRestriction(Restriction restriction) {
        restrictionList.add(restriction);
        setTables.addAll(restriction.getSetTables());
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("Select ");
        if (columnList.size() == 0) {
            builder.append('*');
            builder.append(' ');
        } else {
            for (TableColumn columns : columnList) {
                builder.append(columns.getTable().getName());
                builder.append('.');
                builder.append(columns.getName());
                builder.append(',');
            }
            builder.setCharAt(builder.length() - 1, ' ');
        }
        builder.append("from ");
        for (String table : setTables) {
            builder.append(table);
            builder.append(",");
        }
        builder.setCharAt(builder.length() - 1, ' ');
        if (restrictionList.size() > 0) {
            builder.append("where ");
            for (Restriction restriction : restrictionList) {
                builder.append(restriction.toString());
            }
            builder.append(" or");
        }
        if (builder.charAt(builder.length() - 1) == 'r') {
            return builder.substring(0, builder.length() - 3);
        }
        return builder.toString();
    }

    public List<TableColumn> getColumnList() {
        return columnList;
    }

    public Table getTable() {
        return table;
    }
}
