package airline.criteria.impl;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.criteria.Restrictions;

import java.util.List;
import java.util.LinkedList;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:34:31
 */
public class SelectRequest extends Request {
    private List<TablesColumns> columnList;

    private List<Restrictions> restrictionsList;

    public SelectRequest() {
        columnList = new LinkedList<TablesColumns>();
        restrictionsList = new LinkedList<Restrictions>();
    }

    public void addColumn(TablesColumns column) {
        columnList.add(column);
    }

    public void addRestriction(Restrictions restrictions) {
        restrictionsList.add(restrictions);
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("Select ");
        for (TablesColumns columns : columnList) {
            builder.append(columns.getName());
            builder.append(",");
        }
        builder.setCharAt(builder.length(), ' ');

        builder.append("from ");
        for (TablesColumns columns : columnList) {
            builder.append(columns.getTable().getName());
            builder.append(",");
        }
        
        builder.setCharAt(builder.length(), ' ');
        builder.append("where ");
        for (Restrictions restrictions : restrictionsList) {
            builder.append(restrictions.toString());
        }

        return builder.toString();
    }
}
