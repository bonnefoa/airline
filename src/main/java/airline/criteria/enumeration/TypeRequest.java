package airline.criteria.enumeration;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:25:00
 */
public enum TypeRequest {
    SELECT("SELECT"),INSERT("INSERT"),UPDATE("UPDATE"),DELETE("DELETE"),CREATE_TABLE("CREATE TABLE"),DROP_TABLE("DROP TABLE");

    private String sqlValue;

    TypeRequest(String sqlValue) {
        this.sqlValue = sqlValue;
    }
}
