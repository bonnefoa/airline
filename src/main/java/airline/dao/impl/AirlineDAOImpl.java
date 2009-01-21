package airline.dao.impl;

import airline.connector.Connector;
import airline.dao.AirlineDAO;
import airline.model.TablesEntity;
import airline.model.TablesColumns;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * Dao implementation
 */
public class AirlineDAOImpl implements AirlineDAO {
    private Connection connection;

    @Inject
    public AirlineDAOImpl(Connector connector) {
        connection = connector.getConnection();
    }


    public List<TablesEntity> getTablesEntities() {
        List<TablesEntity> res = new ArrayList<TablesEntity>();
        TablesEntity tablesEntity;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getTables(null, null, null, null);
            while (results.next()) {
                tablesEntity = new TablesEntity();
                tablesEntity.setName(results.getString(TablesEntity.NAME));
                tablesEntity.setType(results.getString(TablesEntity.TYPE));
                tablesEntity.setSchema(results.getString(TablesEntity.SCHEMA));
                res.add(tablesEntity);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TablesColumns> getTablesColumns(String tableName) {
        List<TablesColumns> res = new ArrayList<TablesColumns>();
        TablesColumns tablesColumns;
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet results = metas.getColumns(null, null, tableName, null);
            while (results.next()) {
                tablesColumns = new TablesColumns();
                tablesColumns.setName(results.getString(TablesColumns.NAME));
                tablesColumns.setType(results.getString(TablesColumns.TYPE));
                res.add(tablesColumns);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
