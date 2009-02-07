package airline.manager;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
import airline.criteria.model.Request;
import airline.criteria.model.SelectRequest;
import airline.dao.AirlineDAO;
import airline.dao.TransactionDAO;

import java.util.Map;
import java.util.List;
import java.util.Set;

/**
 * Manager for airline.
 * Communicate with the dao
 */
public interface AirlineManager extends AirlineDAO, TransactionDAO{
}
