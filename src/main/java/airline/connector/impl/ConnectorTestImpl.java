package airline.connector.impl;

import airline.connector.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementation of the connector
 */
public class ConnectorTestImpl implements Connector {
    private Connection connection;
    public static final String IDENTIFIANT = "id";
    public static final String NAME = "nom";
    public static final String TIME = "date";
    public static final String TABLE1 = "TABLE1";
    public static final String TABLE2 = "TABLE2";
    public static final String MESSAGE = "mess";

    public ConnectorTestImpl() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:forumBDD", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Tables already exist");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void initSchema() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
                String.format("CREATE TABLE %s(%s INT  GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY," +
                        "%s VARCHAR(45) NOT NULL," +
                        "%s TIMESTAMP NULL);", TABLE1, IDENTIFIANT, NAME, TIME)
        );
        statement.executeUpdate(
                String.format("CREATE TABLE %s(%s INT  GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY," +
                        "%s VARCHAR(45) NOT NULL," +
                        "%s VARCHAR(45) NOT NULL," +
                        "%s TIMESTAMP NULL);", TABLE2, IDENTIFIANT, NAME, MESSAGE, TIME)
        );

    }

    public void fillTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s) VALUES ('present','2009-01-01 12:00:00')", TABLE1, NAME, TIME));
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s,%s) VALUES ('%s','%s','%s')", TABLE2,
                NAME, MESSAGE, TIME,
                "name", "message", "2009-01-01 12:00:00")
        );
        connection.commit();
    }

    public void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE " + TABLE1);
        statement.executeUpdate("DROP TABLE " + TABLE2);
    }

    public void disconnect() {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeQuery("SHUTDOWN");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}