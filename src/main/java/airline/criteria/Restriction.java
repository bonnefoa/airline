/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package airline.criteria;

import airline.criteria.enumeration.SqlConstraints;
import airline.model.TableColumn;

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

    public void constraint(TableColumn columns1, TableColumn columns2, SqlConstraints constraints) {
        setTables.add(columns1.getTable().getName());
        setTables.add(columns2.getTable().getName());
        writeRequest(columns1.toString(), constraints, columns2.toString());
    }

    public void constraint(TableColumn columns1, String value, SqlConstraints constraints) {
        if (value == null) {
            setTables.add(columns1.getTable().getName());
            writeRequest(columns1.toString(), SqlConstraints.IS, "NULL");
        } else if (!"".equals(value)) {
            setTables.add(columns1.getTable().getName());
            writeRequest(columns1.toString(), constraints, '\'' + value + '\'');
        }else{
            writeRequest("1", SqlConstraints.EQ, "1");
        }
    }

    private void writeRequest(String part1, SqlConstraints sqlConstraints, String part2) {
        query.append(part1);
        query.append(' ');
        query.append(sqlConstraints.getSqlValue());
        query.append(' ');
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
