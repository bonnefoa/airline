package airline.criteria;

import airline.model.TablesColumns;

/**
 * User: sora
 * Date: 31 janv. 2009
 * Time: 11:48:46
 */
public class Restrictions {

    private TablesColumns tablesColumns;
    private String value;
    private SqlConstraints sqlComparator;

    public Restrictions(TablesColumns tablesColumns, String value, SqlConstraints sqlComparator) {
        this.tablesColumns = tablesColumns;
        this.value = value;
        this.sqlComparator = sqlComparator;
    }
}
