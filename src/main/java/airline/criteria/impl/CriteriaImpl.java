package airline.criteria.impl;

import airline.criteria.Criteria;
import airline.criteria.Restrictions;
import airline.model.Table;

import java.util.List;

/**
 * User: sora
 * Date: 31 janv. 2009
 * Time: 11:47:25
 */
public class CriteriaImpl implements Criteria {

    private List<Table> tableList;

    private List<Restrictions> restrictionsList;

    public void addTables(Table tables) {
        tableList.add(tables);
    }

    public void addRestrictions(Restrictions restrictions) {
        restrictionsList.add(restrictions);
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append();
        return builder.toString();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
