package airline.manager.impl;

<<<<<<< HEAD:src/main/java/airline/manager/impl/AirlineManagerImpl.java
import airline.criteria.impl.Request;
import airline.criteria.impl.SelectRequest;
=======
import airline.criteria.model.Request;
import airline.criteria.model.SelectRequest;
>>>>>>> f58379ab525d0346adffe3b9ce8701905011966f:src/main/java/airline/manager/impl/AirlineManagerImpl.java
import airline.dao.AirlineDAO;
import airline.dao.TransactionDAO;
import airline.manager.AirlineManager;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TablesColumns;
<<<<<<< HEAD:src/main/java/airline/manager/impl/AirlineManagerImpl.java
import com.google.inject.Inject;

=======
import airline.model.Transaction;
import com.google.inject.Inject;

import java.util.Date;
>>>>>>> f58379ab525d0346adffe3b9ce8701905011966f:src/main/java/airline/manager/impl/AirlineManagerImpl.java
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public List<TablesColumns> getTablesColumns(Table tables) {
        return airlineDAO.getTablesColumns(tables);
    }

    public List<TableRow> getTablesRows(Table nomTables) {
        return airlineDAO.getTablesRows(nomTables);
    }

    public void executeRequest(Request request) {
        airlineDAO.executeRequest(request);
        Transaction transaction = new Transaction(request.buildQuery(), new Date());
        transactionDAO.addTransaction(transaction);
    }

    public Set<TableRow> executeRequest(SelectRequest selectRequest) {
        return airlineDAO.executeRequest(selectRequest);
    }

    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions();
    }
}
