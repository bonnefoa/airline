package airline.connector;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database connector
 */
public interface Connector {

    Connection getConnection();

    void disconnect();

    void initSchema() throws SQLException;

    void fillTables() throws SQLException;

    void dropTables() throws SQLException;
}
