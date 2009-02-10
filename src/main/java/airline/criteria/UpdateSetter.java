package airline.criteria;

import airline.model.TableColumn;

/**
 * Setter update
 */
public class UpdateSetter {
    private TableColumn tableColumn;

    private String value;

    public UpdateSetter(TableColumn tableColumn, String value) {
        this.tableColumn = tableColumn;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(tableColumn.getTable().getName());
        res.append(".");
        res.append(tableColumn.getName());
        res.append("='");
        res.append(value);
        res.append("' ");
        return res.toString();
    }
}
