package airline.dao;

import airline.model.Table;
import airline.model.TableColumn;
import airline.model.TableRow;
import airline.criteria.model.SelectRequest;
import airline.criteria.model.Request;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.SQLException;

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
     * @return Map of columns name and the tables Columns
     */
    List<TableColumn> getTableColumns(Table tables);

    /**
     * Get all the rows of the given table
     *
     * @param nomTables Table to search
     * @return List of tableRows
     */
    List<TableRow> getTablesRows(Table nomTables);

    /**
     * Execute the given select request
     *
     * @param selectRequest Select request to execute
     * @return Set of tableRows
     * @throws java.sql.SQLException If the request gone wrong
     */
    Set<TableRow> executeRequest(SelectRequest selectRequest) throws SQLException;

    /**
     * Execute the given request
     *
     * @param request Request to execute
     * @throws java.sql.SQLException If the request gone wrong
     */
    void executeRequest(Request request) throws SQLException;
}
