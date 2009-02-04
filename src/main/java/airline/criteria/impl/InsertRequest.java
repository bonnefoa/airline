package airline.criteria.impl;

import airline.model.TablesColumns;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Object request for insert request
 */
public class InsertRequest extends Request {

    private Map<TablesColumns, String> columnsStringMap;

    public InsertRequest() {
        columnsStringMap = new HashMap<TablesColumns, String>();
    }

    public void addNewEntry(TablesColumns columns, String value) {
        columnsStringMap.put(columns, value);
    }

    public String buildQuery() {
        Set<TablesColumns> keys = columnsStringMap.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Insert INTO ");
        stringBuilder.append(keys.iterator().next().getTable().getName());
        stringBuilder.append("(");
        for (TablesColumns columns : keys) {
            stringBuilder.append(columns.getName());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        stringBuilder.append(" VALUES");
        stringBuilder.append("(");
        for (String s : columnsStringMap.values()) {
            stringBuilder.append('\'');
            stringBuilder.append(s);
            stringBuilder.append('\'');
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
