package airline.model;

/**
 * Entities representing the columns of a table
 */
public class TablesColumns {
    public static final String NAME = "COLUMN_NAME";
    public static final String TYPE = "TYPE_NAME";

    private Table tablesEntity;

    private String name;

    private String type;

    private Table tables;

    public Table getTables() {
        return tables;
    }

    public void setTables(Table tables) {
        this.tables = tables;
    }

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

    public Table getTablesEntity() {
        return tablesEntity;
    }

    public void setTablesEntity(Table tablesEntity) {
        this.tablesEntity = tablesEntity;
    }

    @Override
    public String toString() {
        return name + " " + type;
    }
}
