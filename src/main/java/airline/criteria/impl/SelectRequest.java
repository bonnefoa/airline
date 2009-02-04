package airline.criteria.impl;

import airline.model.TablesColumns;
import airline.criteria.Restriction;

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

    private List<Restriction> restrictionList;
    private Set<String> setTables;

    public SelectRequest() {
        columnList = new LinkedList<TablesColumns>();
        restrictionList = new LinkedList<Restriction>();
        setTables = new HashSet<String>();
    }

    public void addColumn(TablesColumns column) {
        columnList.add(column);
    }

    public void addRestriction(Restriction restriction) {
        restrictionList.add(restriction);
        setTables.addAll(restriction.getSetTables());
    }

    public String buildQuery() {
        if(setTables.size()==0){
            for (TablesColumns columns : columnList) {
                setTables.add(columns.getTable().getName());
            }
        }
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
        if (restrictionList.size() > 0) {
            builder.append("where ");
            for (Restriction restriction : restrictionList) {
                builder.append(restriction.toString());
            }
            builder.append(" or");
        }
        if (builder.charAt(builder.length() - 1) == 'r') {
            return builder.substring(0, builder.length() - 3);
        }
        return builder.toString();
    }

    public List<TablesColumns> getColumnList() {
        return columnList;
    }
}
