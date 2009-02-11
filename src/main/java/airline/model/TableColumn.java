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

import java.sql.Types;

/**
 * Entities representing the columns of a table
 */
public class TableColumn {
    public static final String NAME = "COLUMN_NAME";
    public static final String TYPE = "TYPE_NAME";
    public static final String DATA_TYPE = "DATA_TYPE";
    public static final String PRIMARY_KEY = "COLUMN_NAME";

    /**
     * Owner table of the column
     */
    private Table table;

    /**
     * Name of the column
     */
    private String name;

    /**
     * Type of the column
     */
    private String type;

    /**
     * field for primary key of the column
     */
    private boolean primaryKey;

    /**
     * Type of the column following java.sql.Types
     */
    private int dataType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableColumn columns = (TableColumn) o;
        if (name != null ? !name.equals(columns.name) : columns.name != null) return false;
        return !(table != null ? !table.equals(columns.table) : columns.table != null);
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (primaryKey ? 1 : 0);
        result = 31 * result + dataType;
        return result;
    }

    @Override
    public String toString() {
        return table.getName() + "." + name;
    }

    /**
     * Return the sql type to write in request
     *
     * @return Sql type for sql request
     */
    public String getSqlStringDataType() {
        switch (dataType) {
            case Types.INTEGER:
                return "Integer";
            case Types.DATE:
                return "Date";
            case Types.TIMESTAMP:
                return "Timestamp";                
            default:
                return "Varchar";
        }
    }
}
