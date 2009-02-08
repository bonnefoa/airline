package airline.criteria;

import airline.criteria.enumeration.SqlConstraints;
import airline.model.TablesColumns;

import java.util.Set;
import java.util.HashSet;

/**
 * Criteria of requests
 */
public class Restriction {
    private StringBuilder query;

    private Set<String> setTables;

    public Restriction() {
        query = new StringBuilder();
        setTables = new HashSet<String>();
    }

    public void or(Restriction restriction1, Restriction restriction2) {
        setTables.addAll(restriction1.getSetTables());
        setTables.addAll(restriction2.getSetTables());
        query.append(" ( ");
        query.append(restriction1.toString());
        query.append(" OR ");
        query.append(restriction2.toString());
        query.append(" ) ");
    }

    public void and(Restriction restriction1, Restriction restriction2) {
        setTables.addAll(restriction1.getSetTables());
        setTables.addAll(restriction2.getSetTables());
        query.append(" ( ");
        query.append(restriction1.toString());
        query.append(" AND ");
        query.append(restriction2.toString());
        query.append(" ) ");
    }

    public void constraint(TablesColumns columns1, TablesColumns columns2, SqlConstraints constraints) {
        setTables.add(columns1.getTable().getName());
        setTables.add(columns2.getTable().getName());
        writeRequest(columns1.toString(), constraints, columns2.toString());
    }

    public void constraint(TablesColumns columns1, String value, SqlConstraints constraints) {
        setTables.add(columns1.getTable().getName());
        writeRequest(columns1.toString(),constraints, '\''+value+'\'');
    }

    private void writeRequest(String part1, SqlConstraints sqlConstraints, String part2) {
        query.append(part1);
        query.append(sqlConstraints.getSqlValue());
        query.append(part2);
    }

    public Set<String> getSetTables() {
        return setTables;
    }

    @Override
    public String toString() {
        return query.toString();
    }
}
