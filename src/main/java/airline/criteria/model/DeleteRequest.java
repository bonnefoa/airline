package airline.criteria.model;

import airline.criteria.Restriction;
import airline.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Object for deleteRequest
 */
public class DeleteRequest extends Request {

    private Table table;

    private List<Restriction> restrictionList;

    public void addRestriction(Restriction restriction) {
        restrictionList.add(restriction);
    }

    public DeleteRequest(Table table) {
        this.table=table;
        restrictionList = new ArrayList<Restriction>();
    }

    public String buildQuery() {
        StringBuilder res = new StringBuilder();
        res.append("DELETE FROM ");
        res.append(table.getName());
        res.append(' ');
        if (restrictionList.size() > 0) {
            res.append("WHERE ");
            for (Restriction restriction : restrictionList) {
                res.append(restriction.toString());
            }
        }
        return res.toString();
    }
}
