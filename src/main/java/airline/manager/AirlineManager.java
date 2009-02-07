package airline.manager;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
import airline.criteria.model.Request;
import airline.criteria.model.SelectRequest;

import java.util.Map;
import java.util.List;
import java.util.Set;

/**
 * Manager for airline.
 * Communicate with the dao
 */
public interface AirlineManager {
    /**
     * @see airline.dao.AirlineDAO
     */
    Map<String, Table> getTables();

    /**
     * @see airline.dao.AirlineDAO
     */
    List<TablesColumns> getTablesColumns(Table tables);

    /**
     * @see airline.dao.AirlineDAO
     */
    void executeRequest(Request request);

    /**
     * @see airline.dao.AirlineDAO
     */
    Set<TableRow> executeRequest(SelectRequest selectRequest);
}
