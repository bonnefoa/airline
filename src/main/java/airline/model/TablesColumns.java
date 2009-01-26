package airline.model;

/**
 * Entities representing the columns of a table
 */
public class TablesColumns {
    public static final String NAME = "name";
    public static final String TYPE = "type";

    private Tables tablesEntity;

    private String name;

    private String type;

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

    public Tables getTablesEntity() {
        return tablesEntity;
    }

    public void setTablesEntity(Tables tablesEntity) {
        this.tablesEntity = tablesEntity;
    }

    @Override
    public String toString() {
        return name + " " + type;
    }
}
