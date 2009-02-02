package airline.dao;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;

import java.util.List;
import java.util.Map;

/**
 * DAO interface for the dao
 */
public interface AirlineDAO {

    /**
     * Get the map between the tables name and the tables entity
     *
     * @return Map of tables name and the tables entity
     */
    Map<String, Table> getTables();

    /**
     * Get the list of tablesColumns
     *
     * @param tables table to identify
     *
     * @return Map of columns name and the tables Columns
     */
    Map<String, TablesColumns> getTablesColumns(Table tables);

    List<TablesRow> getTablesRows(Table nomTables);
}
