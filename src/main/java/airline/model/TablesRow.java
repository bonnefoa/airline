package airline.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Entities row
 */
public class TablesRow extends HashMap<String,Object>{
    private int lineNumber;


    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

}
