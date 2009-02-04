package airline.model;

/**
 * Entities representing the columns of a table
 */
public class TablesColumns {
    public static final String NAME = "COLUMN_NAME";
    public static final String TYPE = "TYPE_NAME";
    public static final String DATA_TYPE = "DATA_TYPE";

    private Table table;

    private String name;

    private String type;

    private short dataType;

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

    public short getDataType() {
        return dataType;
    }

    public void setDataType(short dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return table.getName() + "." + name;
    }
}
