package airline.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entities row.
 * Map each TableColumn with a String representing the
 * result in database. Since we want to conserve the order, we
 * extends LinkedHashMap
 */
public class TableRow extends LinkedHashMap<TableColumn, String> {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<TableColumn, String> tablesColumnsStringEntry : this.entrySet()) {
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
