package airline.dao.impl;

import airline.connector.Connector;
import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;
import com.google.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dao implementation
 */
public class AirlineDAOImpl implements AirlineDAO {
    private Connection connection;

    @Inject
    public AirlineDAOImpl(Connector connector) {
        connection = connector.getConnection();
    }


    public Map<String, Table> getTablesEntities() {
        Map<String, Table> res = new HashMap<String, Table>();
        Table tablesEntity;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getTables(null, null, null, null);
            while (results.next()) {
                tablesEntity = new Table();
                String name = results.getString(Table.NAME);
                tablesEntity.setName(name);
                tablesEntity.setType(results.getString(Table.TYPE));
                tablesEntity.setSchema(results.getString(Table.SCHEMA));
                res.put(name, tablesEntity);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, TablesColumns> getTablesColumns(Table tables) {
        Map<String, TablesColumns> res = new HashMap<String, TablesColumns>();
        TablesColumns tablesColumns;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getColumns(null, null, tables.getName(), null);
            while (results.next()) {
                tablesColumns = new TablesColumns();
                String name = results.getString(TablesColumns.NAME);
                tablesColumns.setName(name);
                tablesColumns.setType(results.getString(TablesColumns.TYPE));
                tablesColumns.setTable(tables);
                res.put(name, tablesColumns);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TablesRow> getTablesRows(Table tables) {
        List<TablesRow> res = new ArrayList<TablesRow>();
        Map<String, TablesColumns> tablesColumns = getTablesColumns(tables);
        TablesRow tablesRow;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(
                    "SELECT * FROM %s", new Table[]{tables}));
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
