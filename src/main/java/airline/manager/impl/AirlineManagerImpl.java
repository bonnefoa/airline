package airline.manager.impl;

import airline.criteria.model.Request;
import airline.criteria.model.SelectRequest;
import airline.dao.AirlineDAO;
import airline.dao.TransactionDAO;
import airline.manager.AirlineManager;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TableColumn;
import airline.model.Transaction;
import com.google.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.SQLException;

/**
 * Implementation for airline manager
 */
public class AirlineManagerImpl implements AirlineManager {

    private AirlineDAO airlineDAO;

    private TransactionDAO transactionDAO;

    @Inject
    public AirlineManagerImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Inject
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public Map<String, Table> getTables() {
        return airlineDAO.getTables();
    }

    public List<TableColumn> getTableColumns(Table tables) {
        return airlineDAO.getTableColumns(tables);
    }

    public List<TableRow> getTablesRows(Table nomTables) {
        return airlineDAO.getTablesRows(nomTables);
    }

    public void executeRequest(Request request) throws SQLException {
        airlineDAO.executeRequest(request);
        Transaction transaction = new Transaction(request.buildQuery(), new Date());
        transactionDAO.addTransaction(transaction);
    }

    public Set<TableRow> executeRequest(SelectRequest selectRequest) throws SQLException {
        return airlineDAO.executeRequest(selectRequest);
    }

    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions();
    }
}
