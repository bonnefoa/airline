package airline.criteria.model;

import airline.model.Table;
import airline.model.TablesColumns;

import java.util.Map;

/**
 * Request to alter field
 */
public class AlterFieldRequest extends Request {

    private Table table;

    private Map<TablesColumns, TablesColumns> mapColumnses;

    public AlterFieldRequest(Table table, Map<TablesColumns, TablesColumns> mapColumnses) {
        this.table = table;
        this.mapColumnses = mapColumnses;
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<TablesColumns, TablesColumns> tablesColumnsTablesColumnsEntry : mapColumnses.entrySet()) {

            TablesColumns oldColumns = tablesColumnsTablesColumnsEntry.getKey();
            TablesColumns newColumns = tablesColumnsTablesColumnsEntry.getValue();
            if (oldColumns.isPrimaryKey()
                    != newColumns.isPrimaryKey()) {
                res.append("ALTER TABLE ");
                res.append(table.getName());
                if (newColumns.isPrimaryKey()) {
                    res.append(" ADD PRIMARY KEY (");
                } else {
                    res.append(" DROP PRIMARY KEY (");
                }
                res.append(oldColumns.getName());
                res.append(')');
                res.append(';');
            }

            if (!oldColumns.getName().equals(newColumns.getName())) {
                //ALTER TABLE <tablename> ALTER COLUMN <columnname> RENAME TO <newname> 
                res.append("ALTER TABLE ");
                res.append(table.getName());
                res.append(" ALTER COLUMN ");
                res.append(oldColumns.getName());
                res.append(" RENAME TO ");
                res.append(newColumns.getName());
                res.append(';');
            }

            if (oldColumns.getDataType() != newColumns.getDataType()) {
                //ALTER TABLE <table> ALTER COLUMN <column> <new type>

                res.append("ALTER TABLE ");
                res.append(table.getName());
                res.append(" ALTER COLUMN ");
                res.append(newColumns.getName());
                res.append(' ');
                res.append(newColumns.getSqlStringDataType());
                res.append(';');
            }
        }
        return res.toString();
    }
}