package airline.connector;

import java.sql.Connection;

/**
 * Database connector
 */
public interface Connector {

    Connection getConnection();

    void disconnect();
}
