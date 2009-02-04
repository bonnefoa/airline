package airline.dao;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;
import airline.criteria.impl.SelectRequest;
import airline.criteria.impl.Request;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    List<TablesColumns> getTablesColumns(Table tables);

    List<TablesRow> getTablesRows(Table nomTables);

    Set<TablesRow> executeRequest(SelectRequest selectRequest);

    void executeRequest(Request request);
}
