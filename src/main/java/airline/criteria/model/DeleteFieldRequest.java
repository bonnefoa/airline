package airline.criteria.model;

import airline.model.Table;
import airline.model.TableColumn;

import java.util.List;

/**
 * Request to create field
 */
public class DeleteFieldRequest extends Request {

    private Table table;

    private List<TableColumn> tablesColumnses;

    public DeleteFieldRequest(Table table, List<TableColumn> tablesColumnses) {
        this.table = table;
        this.tablesColumnses = tablesColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        for (TableColumn tablesColumnse : tablesColumnses) {
            res.append("ALTER TABLE ");
            res.append(table.getName());
            res.append(" DROP ");
            res.append(tablesColumnse.getName());
            res.append(';');
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}