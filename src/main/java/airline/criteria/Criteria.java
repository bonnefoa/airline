package airline.criteria;

import airline.model.Table;

/**
 * User: sora
 * Date: 31 janv. 2009
 * Time: 11:47:14
 */
public interface Criteria {
    void addTables(Table tables);

    void addRestrictions(Restrictions restrictions);

    String buildQuery();
}
