package airline.dao.impl;

import airline.dao.TransactionDAO;
import airline.model.Transaction;
import airline.connector.Connector;
import com.google.inject.Inject;

import java.util.List;
import java.util.LinkedList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Implementation for the dao of transaction
 */
public class TransactionDAOImpl implements TransactionDAO {

    private Connector connector;

    @Inject
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public void addTransaction(Transaction transaction) {
        try {
            Connection connection = connector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(transaction.getInsertRequest());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactions() {
        List<Transaction> res = new LinkedList<Transaction>();
        Connection connection = connector.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(
                    "SELECT * FROM %s", Transaction.TRANSACTION_TABLE));
            while (result.next()) {
                Transaction transaction = new Transaction(
                        result.getInt(Transaction.IDENTIFIANT),
                        result.getString(Transaction.DESCRIPTION),
                        result.getTimestamp(Transaction.TIME));
                res.add(transaction);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
