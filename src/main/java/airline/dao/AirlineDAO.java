package airline.dao;

import airline.model.Tables;
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
    Map<String, Tables> getTablesEntities();

    /**
     * Get the list of tablesColumns
     *
     * @param nomTables Name of the tables
     *
     * @return Map of columns name and the tables Columns
     */
    Map<String, TablesColumns> getTablesColumns(String nomTables);

    List<TablesRow> getTablesRows(String nomTables);
}
