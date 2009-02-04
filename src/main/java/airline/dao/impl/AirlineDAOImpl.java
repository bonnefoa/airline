package airline.dao.impl;

import airline.connector.Connector;
import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TablesRow;
import airline.criteria.impl.SelectRequest;
import airline.criteria.impl.Request;
import com.google.inject.Inject;

import java.sql.*;
import java.util.*;

/**
 * Dao implementation
 */
public class AirlineDAOImpl implements AirlineDAO {
    private Connection connection;

    @Inject
    public AirlineDAOImpl(Connector connector) {
        connection = connector.getConnection();
    }


    public Map<String, Table> getTables() {
        Map<String, Table> res = new LinkedHashMap<String, Table>();
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

    public List<TablesColumns> getTablesColumns(Table tables) {
        List<TablesColumns> res = new LinkedList<TablesColumns>();
        TablesColumns tablesColumns;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getColumns(null, null, tables.getName(), null);
            while (results.next()) {
                tablesColumns = new TablesColumns();
                tablesColumns.setName(results.getString(TablesColumns.NAME));
                tablesColumns.setType(results.getString(TablesColumns.TYPE));
                tablesColumns.setDataType(results.getShort(TablesColumns.DATA_TYPE));
                tablesColumns.setTable(tables);
                res.add(tablesColumns);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeRequest(Request request) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(request.buildQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<TablesRow> executeRequest(SelectRequest selectRequest) {
        Set<TablesRow> res = new LinkedHashSet<TablesRow>();
        TablesRow tablesRow;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectRequest.buildQuery());
            while (result.next()) {
                tablesRow = new TablesRow();
                for (TablesColumns tablesColumns : selectRequest.getColumnList()) {
                    Object obj = result.getObject(tablesColumns.getName());
                    tablesRow.put(tablesColumns, obj.toString());
                }
                res.add(tablesRow);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TablesRow> getTablesRows(Table tables) {
        List<TablesRow> res = new LinkedList<TablesRow>();
        List<TablesColumns> tablesColumns = getTablesColumns(tables);
        TablesRow tablesRow;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(
                    "SELECT * FROM %s", tables.getName()));
            while (result.next()) {
                tablesRow = new TablesRow();
                for (TablesColumns columnsEntry : tablesColumns) {
                    Object obj = result.getObject(columnsEntry.getName());
                    tablesRow.put(columnsEntry, obj.toString());
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
