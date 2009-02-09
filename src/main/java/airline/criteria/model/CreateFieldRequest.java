package airline.criteria.model;

import airline.model.Table;
import airline.model.TablesColumns;

import java.util.List;

/**
 * Request to create field
 */
public class CreateFieldRequest extends Request {

    private Table table;

    private List<TablesColumns> tablesColumnses;

    public CreateFieldRequest(Table table, List<TablesColumns> tablesColumnses) {
        this.table = table;
        this.tablesColumnses = tablesColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        for (TablesColumns tablesColumnse : tablesColumnses) {
            res.append("ALTER TABLE ");
            res.append(table.getName());
            res.append(" ADD ");
            res.append(tablesColumnse.getName());
            res.append(' ');
            res.append(tablesColumnse.getSqlStringDataType());
            res.append(';');
        }
        return res.toString();
    }
}