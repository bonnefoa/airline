package airline.criteria;

import airline.criteria.enumeration.SqlConstraints;
import airline.model.TablesColumns;

/**
 * Restrictions for where clauses
 */
public class Restrictions {
    private TablesColumns tablesColumns;
    private TablesColumns tablesColumns2;
    private String value;
    private SqlConstraints sqlComparator;

    public Restrictions(TablesColumns tablesColumns, String value, SqlConstraints sqlComparator) {
        this.tablesColumns = tablesColumns;
        this.value = value;
        this.sqlComparator = sqlComparator;
    }

    public Restrictions(TablesColumns tablesColumns, TablesColumns tablesColumns2, SqlConstraints sqlComparator) {
        this.tablesColumns = tablesColumns;
        this.tablesColumns2 = tablesColumns2;
        this.sqlComparator = sqlComparator;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(tablesColumns.getTable().getName());
        res.append('.');
        res.append(tablesColumns.getName());
        res.append(' ');
        res.append(sqlComparator.getSqlValue());
        if (tablesColumns2 == null) {
            res.append('\'');
            res.append(value);
            res.append('\'');
        } else {
            res.append(tablesColumns2.getTable().getName());
            res.append('.');
            res.append(tablesColumns2.getName());
        }

        return res.toString();
    }
}
