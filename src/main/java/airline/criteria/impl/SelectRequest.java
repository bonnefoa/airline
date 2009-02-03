package airline.criteria.impl;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.criteria.Restrictions;

import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:34:31
 */
public class SelectRequest extends Request {
    private List<TablesColumns> columnList;

    private List<Restrictions> restrictionsList;
    private Set<String> setTables;

    public SelectRequest() {
        columnList = new LinkedList<TablesColumns>();
        restrictionsList = new LinkedList<Restrictions>();
        setTables = new HashSet<String>();
    }

    public void addColumn(TablesColumns column) {
        columnList.add(column);
        setTables.add(column.getTable().getName());
    }

    public void addRestriction(Restrictions restrictions) {
        restrictionsList.add(restrictions);
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("Select ");
        for (TablesColumns columns : columnList) {
            builder.append(columns.getTable().getName());
            builder.append('.');
            builder.append(columns.getName());
            builder.append(',');
        }
        builder.setCharAt(builder.length() - 1, ' ');

        builder.append("from ");
        for (String table : setTables) {
            builder.append(table);
            builder.append(",");
        }
        builder.setCharAt(builder.length() - 1, ' ');
        if (restrictionsList.size() > 0) {
            builder.append("where ");
            for (Restrictions restrictions : restrictionsList) {
                builder.append(restrictions.toString());
            }
        }
        return builder.toString();
    }

    public List<TablesColumns> getColumnList() {
        return columnList;
    }
}
