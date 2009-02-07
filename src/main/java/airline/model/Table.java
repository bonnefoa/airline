package airline.model;

/**
 * Entity of a table
 */
public class Table {

    public static final String NAME = "TABLE_NAME";
    public static final String TYPE = "TABLE_TYPE";
    public static final String SCHEMA = "TABLE_SCHEM";

    /**
     * Name of the table
     */
    private String name;

    /**
     * Type of the table
     */
    private String type;

    /**
     * Scheme of the table
     */
    private String schema;

    public Table(String name) {
        this.name = name;
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

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return name + " " + type + " " + schema;
    }
}
