package airline.criteria.impl;

import airline.model.Table;
import airline.model.TablesColumns;

import java.sql.Types;
import java.util.List;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:35:52
 */
public class CreateTableRequest extends Request {

    private Table table;

    private List<TablesColumns> tablesColumnses;

    public CreateTableRequest(Table table, List<TablesColumns> tablesColumnses) {
        this.table = table;
        this.tablesColumnses = tablesColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("CREATE TABLE ");
        res.append(table.getName());
        for (TablesColumns tablesColumnse : tablesColumnses) {
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
            res.append(',');
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
