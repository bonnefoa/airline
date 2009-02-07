package airline.dao;

import airline.BaseClass;
import airline.criteria.enumeration.TypeRequest;
import airline.model.Table;
import airline.model.Transaction;
import airline.connector.Connector;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.google.inject.Inject;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * User: sora
 * Date: 7 févr. 2009
 * Time: 16:06:06
 */
public class TransactionDAOTest extends BaseClass {
    private TransactionDAO transactionDAO;

    @Test
    public void testAddOneTransaction() {
        Transaction transaction = new Transaction(
                "Description",
                new Date());
        transactionDAO.addTransaction(transaction);
        List<Transaction> list = transactionDAO.getTransactions();
        assertEquals(1, list.size());
        assertEquals(transaction,list.get(0));
    }

    @Test
    public void testAddTwoTransactions() {
        Transaction transaction = new Transaction(
                "Description",
                new Date());
        Transaction transaction2 = new Transaction(
                "Description22",
                new Date());
        transactionDAO.addTransaction(transaction);
        transactionDAO.addTransaction(transaction2);
        List<Transaction> list = transactionDAO.getTransactions();
        assertEquals(2, list.size());
        assertEquals(transaction,list.get(0));
        assertEquals(transaction2,list.get(1));
    }

    @Inject
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

}
