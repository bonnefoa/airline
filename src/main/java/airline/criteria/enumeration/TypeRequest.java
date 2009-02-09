package airline.criteria.enumeration;

import java.util.Map;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:25:00
 */
public enum TypeRequest {
    SELECT("SELECT", 0), INSERT("INSERT", 1), UPDATE("UPDATE", 2), DELETE("DELETE", 3), CREATE_TABLE("CREATE TABLE", 4), DROP_TABLE("DROP TABLE", 5);

    private String sqlValue;

    private int typeRequest;

    private static Map<Integer, TypeRequest> lookup;

    static {
        lookup = new HashMap<Integer, TypeRequest>();
        Iterator<TypeRequest> it = EnumSet.allOf(TypeRequest.class).iterator();
        while (it.hasNext()) {
            TypeRequest type = it.next();
            lookup.put(type.getTypeRequest(), type);
        }

    }

    TypeRequest(String sqlValue, int typeRequest) {
        this.sqlValue = sqlValue;
        this.typeRequest = typeRequest;
    }

    public String getSqlValue() {
        return sqlValue;
    }

    public int getTypeRequest() {
        return typeRequest;
    }

    public static TypeRequest lookupTypeRequest(int type) {
        if (lookup.containsKey(type)) {
            return lookup.get(type);
        }
        return null;
    }
}
