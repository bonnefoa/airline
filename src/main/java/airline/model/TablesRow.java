package airline.model;

import java.util.List;

/**
 * Entities row
 */
public class TablesRow {
    private List<Tables> entityList;

    public TablesRow(List<Tables> entityList) {
        this.entityList = entityList;
    }

    public List<Tables> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Tables> entityList) {
        this.entityList = entityList;
    }
}
