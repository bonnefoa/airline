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

package airline.model;

import airline.criteria.enumeration.TypeRequest;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Transaction model
 */
public class Transaction {

    public static final String IDENTIFIANT = "ID";
    public static final String TIME = "DATE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TRANSACTION_TABLE = "TRANSACTIONS";

    private Integer id;

    private String description;

    private Date date;

    public Transaction(Integer id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public Transaction(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getInsertRequest() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Insert INTO ");
        stringBuilder.append(TRANSACTION_TABLE);
        stringBuilder.append('(');
        stringBuilder.append(TIME);
        stringBuilder.append(',');
        stringBuilder.append(DESCRIPTION);
        stringBuilder.append(')');
        stringBuilder.append(" VALUES");
        stringBuilder.append('(');
        stringBuilder.append('\'');
        stringBuilder.append(new Timestamp(date.getTime()));
        stringBuilder.append('\'');
        stringBuilder.append(',');
        stringBuilder.append('\'');
        stringBuilder.append(description);
        stringBuilder.append('\'');
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
