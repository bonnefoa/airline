package airline.criteria.impl;

import airline.criteria.UpdateSetter;
import airline.criteria.Restriction;
import airline.model.TablesColumns;

import java.util.List;
import java.util.ArrayList;

/**
 * User: sora
 * Date: 2 févr. 2009
 * Time: 19:35:31
 */
public class UpdateRequest extends Request {

    private List<UpdateSetter> updateSetterList;

    private List<Restriction> restrictionList;

    public UpdateRequest() {
        updateSetterList = new ArrayList<UpdateSetter>();
        restrictionList = new ArrayList<Restriction>();
    }

    public void addUpdateSetter(UpdateSetter updateSetter) {
        updateSetterList.add(updateSetter);
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("Update ");
        builder.append(" set ");        
        for (UpdateSetter updateSetter : updateSetterList) {
            builder.append(updateSetter.toString());
            builder.append(" ,");
        }
        builder.deleteCharAt(builder.length()-1);
        if (restrictionList.size() > 0) {
            builder.append("where ");
            for (Restriction restriction : restrictionList) {
                builder.append(restriction.toString());
            }
            builder.append(" or");
        }
        if (builder.charAt(builder.length() - 1) == 'r') {
            builder.delete(0, builder.length() - 3);
        }
        return builder.toString();
    }
}
