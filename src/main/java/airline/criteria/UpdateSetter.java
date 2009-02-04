package airline.criteria;

import airline.model.TablesColumns;

/**
 * Setter update
 */
public class UpdateSetter {
    private TablesColumns tablesColumns;

    private String value;

    public UpdateSetter(TablesColumns tablesColumns, String value) {
        this.tablesColumns = tablesColumns;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(tablesColumns.getTable().getName());
        res.append(".");
        res.append(tablesColumns.getName());
        res.append("='");
        res.append(value);
        res.append("' ");
        return res.toString();
    }
}
