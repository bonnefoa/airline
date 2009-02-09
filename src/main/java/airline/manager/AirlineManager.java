package airline.manager;

import airline.dao.AirlineDAO;
import airline.dao.TransactionDAO;

/**
 * Manager for airline.
 * Communicate with the dao
 */
public interface AirlineManager extends AirlineDAO, TransactionDAO {
}
