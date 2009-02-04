package airline.criteria.impl;

import airline.model.Table;
import airline.model.TablesColumns;

import java.sql.Types;
import java.util.List;

/**
 * Request to create field
 */
public class DeleteFieldRequest extends Request {

    private Table table;

    private List<TablesColumns> tablesColumnses;

    public DeleteFieldRequest(Table table, List<TablesColumns> tablesColumnses) {
        this.table = table;
        this.tablesColumnses = tablesColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("ALTER TABLE ");
        res.append(table.getName());
        res.append(" DROP ");
        for (TablesColumns tablesColumnse : tablesColumnses) {
            res.append(tablesColumnse.getName());
            res.append(' ');
            res.append(',');
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}