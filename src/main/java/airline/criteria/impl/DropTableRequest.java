package airline.criteria.impl;

import airline.model.Table;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:36:01
 */
public class DropTableRequest extends Request {
    private Table table;

    public DropTableRequest(Table table) {
        this.table = table;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("DROP TABLE ");
        res.append(table.getName());
        return res.toString();
    }
}
