package airline.manager.impl;

import airline.criteria.impl.Request;
import airline.criteria.impl.SelectRequest;
import airline.dao.AirlineDAO;
import airline.manager.AirlineManager;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TablesColumns;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation for airline manager
 */
public class AirlineManagerImpl implements AirlineManager {

    private AirlineDAO airlineDAO;

    @Inject
    public AirlineManagerImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public Map<String, Table> getTables(){
        return airlineDAO.getTables();
    }

    public List<TablesColumns> getTablesColumns(Table tables){
        return airlineDAO.getTablesColumns(tables);
    }

    public void executeRequest(Request request) {
        airlineDAO.executeRequest(request);
    }

    public Set<TableRow> executeRequest(SelectRequest selectRequest) {
        return airlineDAO.executeRequest(selectRequest);
    }
}
