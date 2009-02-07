package airline.dao;

import airline.model.Transaction;

import java.util.List;

/**
 * DAO interface for the dao
 */
public interface TransactionDAO {
    /**
     * Add the transaction
     */
    void addTransaction(Transaction transaction);

    /**
     * Get the transactions made
     */
    List<Transaction> getTransactions();
}