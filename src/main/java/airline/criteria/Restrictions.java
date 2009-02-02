package airline.criteria;

import airline.criteria.enumeration.SqlConstraints;
import airline.model.TablesColumns;

/**
 * Restrictions for where clauses
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
