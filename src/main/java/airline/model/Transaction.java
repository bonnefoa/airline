package airline.model;

import airline.criteria.enumeration.TypeRequest;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Transaction model
 */
public class Transaction {

    public static final String IDENTIFIANT = "ID";
    public static final String TYPE = "TYPE";
    public static final String TIME = "DATE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TRANSACTION_TABLE = "TRANSACTIONS";

    private Integer id;

    private String description;

    private Date date;

    private TypeRequest request;

    public Transaction(Integer id, String description, Date date, TypeRequest request) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.request = request;
    }

    public Transaction(String description, Date date, TypeRequest request) {
        this.description = description;
        this.date = date;
        this.request = request;
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

    public TypeRequest getRequest() {
        return request;
    }

    public void setRequest(TypeRequest request) {
        this.request = request;
    }

    public String getInsertRequest() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Insert INTO ");
        stringBuilder.append(TRANSACTION_TABLE);
        stringBuilder.append('(');
        stringBuilder.append(TYPE);
        stringBuilder.append(',');
        stringBuilder.append(TIME);
        stringBuilder.append(',');
        stringBuilder.append(DESCRIPTION);
        stringBuilder.append(')');
        stringBuilder.append(" VALUES");
        stringBuilder.append('(');
        stringBuilder.append(request.getTypeRequest());
        stringBuilder.append(',');
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
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (request != that.request) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (request != null ? request.hashCode() : 0);
        return result;
    }
}
