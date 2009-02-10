package airline.criteria.model;

import airline.model.Table;
import airline.model.TableColumn;

import java.sql.Types;
import java.util.List;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:35:52
 */
public class CreateTableRequest extends Request {

    private Table table;

    private List<TableColumn> tablesColumnses;

    public CreateTableRequest(Table table, List<TableColumn> tablesColumnses) {
        this.table = table;
        this.tablesColumnses = tablesColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("CREATE TABLE ");
        res.append(table.getName());
        res.append(" (");

        TableColumn primaryKey = null;

        for (TableColumn tablesColumnse : tablesColumnses) {
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

            if (tablesColumnse.isPrimaryKey()) {
                primaryKey = tablesColumnse;
            }
        }
        if (primaryKey != null) {
            res.append("primary key(");
            res.append(primaryKey.getName());
            res.append(')');
        } else {
            res.deleteCharAt(res.length() - 1);
        }
        res.append(" )");
        return res.toString();
    }
}
