package airline.criteria.model;

import airline.model.TableColumn;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Object request for insert request
 */
public class InsertRequest extends Request {

    private Map<TableColumn, String> columnsStringMap;

    public InsertRequest() {
        columnsStringMap = new HashMap<TableColumn, String>();
    }

    public void addNewEntry(TableColumn columns, String value) {
        columnsStringMap.put(columns, value);
    }

    public String buildQuery() {
        Set<TableColumn> keys = columnsStringMap.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Insert INTO ");
        stringBuilder.append(keys.iterator().next().getTable().getName());
        stringBuilder.append("(");
        for (TableColumn columns : keys) {
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
