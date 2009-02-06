package airline.criteria.impl;

import airline.model.Table;
import airline.model.TablesColumns;

import java.sql.Types;
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
            switch (tablesColumnse.getDataType()) {
                case Types.INTEGER:
                    res.append("Integer");
                    break;
                case Types.DATE:
                    res.append("Date");
                    break;
                default:
                    res.append("Varchar");
                    break;
            }
            res.append(';');
        }
        return res.toString();
    }
}