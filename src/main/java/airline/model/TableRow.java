package airline.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entities row
 */
public class TableRow extends LinkedHashMap<TablesColumns, String> {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<TablesColumns, String> tablesColumnsStringEntry : this.entrySet()) {
            builder.append(tablesColumnsStringEntry.getKey());
            builder.append(' ');
            builder.append(':');
            builder.append(' ');
            builder.append(tablesColumnsStringEntry.getValue());
            builder.append(' ');
        }
        return builder.toString();
    }
}
