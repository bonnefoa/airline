/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package airline.connector.impl;

import airline.connector.Connector;
import static airline.model.Transaction.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementation of the connector
 */
public class ConnectorTestImpl implements Connector {
    private Connection connection;
    public static final String IDENTIFIANT = "ID";
    public static final String NAME = "NOM";
    public static final String NAME2 = "NOM2";
    public static final String TIME = "DATE";
    public static final String TABLE1 = "TABLE1";
    public static final String TABLE2 = "TABLE2";
    public static final String MESSAGE = "MESS";

    public ConnectorTestImpl() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:airline", "sa", "");
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
                        "%s TIMESTAMP NULL);", TABLE2, IDENTIFIANT, NAME2, MESSAGE, TIME)
        );
        statement.executeUpdate(
                String.format("CREATE TABLE %s(%s INT  GENERATED BY DEFAULT AS IDENTITY(START WITH 0) PRIMARY KEY," +
                        "%s TIMESTAMP NULL," +
                        "%s VARCHAR(250) NULL);", TRANSACTION_TABLE, IDENTIFIANT, TIME, DESCRIPTION)
        );
    }

    public void fillTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s) VALUES ('present','2009-01-01 12:00:00')", TABLE1, NAME, TIME));
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s,%s) VALUES ('%s','%s','%s')", TABLE2,
                NAME2, MESSAGE, TIME,
                "name", "message", "2009-01-01 12:00:00")
        );
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s,%s) VALUES ('%s','%s','%s')", TABLE2,
                NAME2, MESSAGE, TIME,
                "name2", "message2", "2009-01-01 12:00:01")
        );
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s,%s) VALUES ('%s','%s','%s')", TABLE2,
                NAME2, MESSAGE, TIME,
                "name3", "message3", "2009-01-01 12:00:03")
        );
        statement.executeUpdate(String.format("INSERT INTO %s (%s, %s,%s) VALUES ('%s','%s','%s')", TABLE2,
                NAME2, MESSAGE, TIME,
                "name4", "message4", "2009-01-01 12:00:04")
        );

        connection.commit();
    }

    public void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("DROP TABLE " + TABLE1);
        } finally {
            try {
                statement.executeUpdate("DROP TABLE " + TABLE2);
            } finally {
                statement.executeUpdate("DROP TABLE " + TRANSACTION_TABLE);
            }
        }
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