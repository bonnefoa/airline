package airline.dao.impl;

import airline.connector.Connector;
import airline.dao.AirlineDAO;
import airline.model.TablesColumns;
import airline.model.Tables;
import airline.model.TablesRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.inject.Inject;

/**
 * Dao implementation
 */
public class AirlineDAOImpl implements AirlineDAO {
    private Connection connection;

    @Inject
    public AirlineDAOImpl(Connector connector) {
        connection = connector.getConnection();
    }


    public Map<String, Tables> getTablesEntities() {
        Map<String, Tables> res = new HashMap<String, Tables>();
        Tables tablesEntity;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getTables(null, null, null, null);
            while (results.next()) {
                tablesEntity = new Tables();
                String name = results.getString(Tables.NAME);
                tablesEntity.setName(name);
                tablesEntity.setType(results.getString(Tables.TYPE));
                tablesEntity.setSchema(results.getString(Tables.SCHEMA));
                res.put(name, tablesEntity);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, TablesColumns> getTablesColumns(String tableName) {
        Map<String, TablesColumns> res = new HashMap<String, TablesColumns>();
        TablesColumns tablesColumns;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getColumns(null, null, tableName, null);
            while (results.next()) {
                tablesColumns = new TablesColumns();
                String name = results.getString(TablesColumns.NAME);
                tablesColumns.setName(name);
                tablesColumns.setType(results.getString(TablesColumns.TYPE));
                res.put(name, tablesColumns);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TablesRow> getTablesRows(String nomTables) {
        List<TablesRow> res = new ArrayList<TablesRow>();
        Map<String, TablesColumns> tablesColumns = getTablesColumns(nomTables);
        TablesRow tablesRow;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(
                    "SELECT * FROM %s", nomTables));
            while (result.next()) {
                tablesRow = new TablesRow();
                for (Map.Entry<String, TablesColumns> columnsEntry : tablesColumns.entrySet()) {
                    Object obj = result.getObject(columnsEntry.getKey());
                    tablesRow.put(columnsEntry.getKey(), obj);
                }
                res.add(tablesRow);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
