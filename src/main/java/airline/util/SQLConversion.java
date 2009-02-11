package airline.util;

import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 11 févr. 2009
 * Time: 14:07:24
 * To change this template use File | Settings | File Templates.
 */
public class SQLConversion {

    public static String sqlTypeToString(int sqltype) {
        switch (sqltype) {

            case Types.BIT:
                return "BIT";
            case Types.TINYINT:
                return "TINYINT";
            case Types.SMALLINT:
                return "SMALLINT";
            case Types.INTEGER:
                return "INTEGER";
            case Types.BIGINT:
                return "BIGINT";
            case Types.FLOAT:
                return "FLOAT";
            case Types.REAL:
                return "REAL";
            case Types.DOUBLE:
                return "DOUBLE";
            case Types.NUMERIC:
                return "NUMERIC";
            case Types.DECIMAL:
                return "DECIMAL";
            case Types.CHAR:
                return "CHAR";
            case Types.VARCHAR:
                return "VARCHAR";
            case Types.LONGVARCHAR:
                return "LONGVARCHAR";
            case Types.DATE:
                return "DATE";
            case Types.TIME:
                return "TIME";
            case Types.TIMESTAMP:
                return "TIMESTAMP";
            case Types.BINARY:
                return "BINARY";
            case Types.VARBINARY:
                return "VARBINARY";
            case Types.LONGVARBINARY:
                return "LONGVARBINARY";
            case Types.BLOB:
                return "BLOB";
            case Types.BOOLEAN:
                return "BOOLEAN";
        }

        return null;
    }

    public static int stringToSqlType(String type) {

        if (type.equals("BIT")) {
            return Types.BIT;
        }
        if (type.equals("TINYINT")) {
            return Types.TINYINT;
        }
        if (type.equals("SMALLINT")) {
            return Types.SMALLINT;
        }
        if (type.equals("INTEGER")) {
            return Types.INTEGER;
        }
        if (type.equals("BIGINT")) {
            return Types.BIGINT;
        }
        if (type.equals("FLOAT")) {
            return Types.FLOAT;
        }
        if (type.equals("REAL")) {
            return Types.REAL;
        }
        if (type.equals("DOUBLE")) {
            return Types.DOUBLE;
        }
        if (type.equals("NUMERIC")) {
            return Types.NUMERIC;
        }
        if (type.equals("DECIMAL")) {
            return Types.DECIMAL;
        }
        if (type.equals("CHAR")) {
            return Types.CHAR;
        }
        if (type.equals("VARCHAR")) {
            return Types.VARCHAR;
        }
        if (type.equals("LONGVARCHAR")) {
            return Types.LONGVARCHAR;
        }
        if (type.equals("DATE")) {
            return Types.DATE;
        }
        if (type.equals("TIME")) {
            return Types.TIME;
        }
        if (type.equals("TIMESTAMP")) {
            return Types.TIMESTAMP;
        }
        if (type.equals("BINARY")) {
            return Types.BINARY;
        }
        if (type.equals("VARBINARY")) {
            return Types.VARBINARY;
        }
        if (type.equals("LONGVARBINARY")) {
            return Types.LONGVARBINARY;
        }
        if (type.equals("BLOB")) {
            return Types.BLOB;
        }
        if (type.equals("BOOLEAN")) {
            return Types.BOOLEAN;
        }
        return Types.VARCHAR;
    }

    public static Map<Integer, String> getTypeList() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(Types.VARCHAR, "VARCHAR");

        map.put(Types.BIT, "BIT");
        map.put(Types.TINYINT, "TINYINT");
        map.put(Types.SMALLINT, "SMALLINT");
        map.put(Types.INTEGER, "INTEGER");
        map.put(Types.BIGINT, "BIGINT");
        map.put(Types.FLOAT, "FLOAT");
        map.put(Types.REAL, "REAL");
        map.put(Types.DOUBLE, "DOUBLE");
        map.put(Types.NUMERIC, "NUMERIC");
        map.put(Types.DECIMAL, "DECIMAL");
        map.put(Types.CHAR, "CHAR");
        map.put(Types.VARCHAR, "VARCHAR");
        map.put(Types.LONGVARCHAR, "LONGVARCHAR");
        map.put(Types.DATE, "DATE");
        map.put(Types.TIME, "TIME");
        map.put(Types.TIMESTAMP, "TIMESTAMP");
        map.put(Types.BINARY, "BINARY");
        map.put(Types.VARBINARY, "VARBINARY");
        map.put(Types.LONGVARBINARY, "LONGVARBINARY");
        map.put(Types.BLOB, "BLOB");
        map.put(Types.BOOLEAN, "BOOLEAN");

        return map;

    }
}
