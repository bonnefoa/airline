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

package airline.tables.context;

import airline.model.Table;
import airline.model.TableRow;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Un gestionnaire d'action sur des lignes.
 */
public abstract class RowContextHandler extends ContextHandler {

    protected void init(Action action) {
        init(Context.FIELD, action);
    }

    /**
     * Vérifie le contexte Row.
     * Ce contexte correspond aux cas suivants :
     * /table/---/row/add
     * /table/---/row/---/edit
     * /table/---/row/---/delete
     * On vérifie donc l'existence de la table et la validité du tuple.
     */
    public MessageError checkContext(ServletRequest req) {
        Table table = getTable(req);
        if (table != null) {
            req.setAttribute("url.table", table);
        } else {
            return MessageError.INEXISTANT_TABLE;
        }

        Action action = (Action) req.getAttribute("url.action");
        if (action == Action.ADD) {
            return null;
        } else {
            int row = Integer.parseInt((String) req.getAttribute("url.row"));
            List<TableRow> rows = airlineDAO.getTablesRows(table);
            if (row < rows.size()) {
                req.setAttribute("url.row", rows.get(row));
                req.setAttribute("url.rowNb", row);
                return null;
            } else {
                return MessageError.INEXISTANT_ROW;
            }
        }
    }

    private Table getTable(ServletRequest req) {
        String tableName = (String) req.getAttribute("url.table");
        Map<String, Table> tables = airlineDAO.getTables();
        return tables.get(tableName);
    }
}
