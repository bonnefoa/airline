package airline.dao.impl;

import airline.connector.Connector;
import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
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
        List<String> primaryKeys = new ArrayList<String>();
        TablesColumns tablesColumns;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results;
            results = metas.getPrimaryKeys(null, null, tables.getName());
            while (results.next()) {
                primaryKeys.add(results.getString(TablesColumns.NAME));
            }

            results = metas.getColumns(null, null, tables.getName(), null);
            while (results.next()) {
                tablesColumns = new TablesColumns();
                String name = results.getString(TablesColumns.NAME);
                tablesColumns.setName(name);
                tablesColumns.setType(results.getString(TablesColumns.TYPE));
                tablesColumns.setDataType(results.getShort(TablesColumns.DATA_TYPE));
                tablesColumns.setTable(tables);
                tablesColumns.setPrimaryKey(primaryKeys.contains(name));
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
            System.out.println("Request :" + request.buildQuery());
            statement.execute(request.buildQuery());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Set<TableRow> executeRequest(SelectRequest selectRequest) {
        Set<TableRow> res = new LinkedHashSet<TableRow>();
        TableRow tableRow;
        try {
            Statement statement = connection.createStatement();
            System.out.println("Request :" + selectRequest.buildQuery());
            ResultSet result = statement.executeQuery(selectRequest.buildQuery());
            while (result.next()) {
                tableRow = new TableRow();
                for (TablesColumns tablesColumns : selectRequest.getColumnList()) {
                    Object obj = result.getObject(tablesColumns.getName());
                    tableRow.put(tablesColumns, obj.toString());
                }
                res.add(tableRow);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TableRow> getTablesRows(Table tables) {
        List<TableRow> res = new LinkedList<TableRow>();
        List<TablesColumns> tablesColumns = getTablesColumns(tables);
        TableRow tableRow;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(
                    "SELECT * FROM %s", tables.getName()));
            while (result.next()) {
                tableRow = new TableRow();
                for (TablesColumns columnsEntry : tablesColumns) {
                    Object obj = result.getObject(columnsEntry.getName());
                    tableRow.put(columnsEntry, obj.toString());
                }
                res.add(tableRow);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
