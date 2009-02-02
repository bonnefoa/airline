package airline.criteria;

/**
 * User: sora
 * Date: 31 janv. 2009
 * Time: 11:45:28
 */
public enum SqlConstraints {
    GT(">"),GE(">="),
    LT("<"),LE("<="),
    EQ("="),NEQ("!="),
    LIKE("like"),ILIKE("ilike");

    private String sqlValue;

    SqlConstraints(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public String getSqlValue() {
        return sqlValue;
    }
}
