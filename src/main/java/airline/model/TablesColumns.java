package airline.model;

/**
 * Entities representing the columns of a table
 */
public class TablesColumns {
    public static final String NAME = "COLUMN_NAME";
    public static final String TYPE = "TYPE_NAME";
    public static final String DATA_TYPE = "DATA_TYPE";
    public static final String PRIMARY_KEY = "COLUMN_NAME";

    private Table table;

    private String name;

    private String type;

    private boolean primaryKey;

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

        TablesColumns columns = (TablesColumns) o;

        if (name != null ? !name.equals(columns.name) : columns.name != null) return false;
        if (table != null ? !table.equals(columns.table) : columns.table != null) return false;

        return true;
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
}
