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
import airline.model.TableColumn;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Un gestionnaire d'action sur des Champs.
 */
public abstract class FieldContextHandler extends ContextHandler {

    protected void init(Action action) {
        init(Context.FIELD, action);
    }

    /**
     * Vérifie le contexte Row.
     * Ce contexte correspond aux cas suivants :
     * /table/---/field/add
     * /table/---/field/---/edit
     * /table/---/field/---/delete
     * On vérifie donc l'existence de la table et la validité de la colonne.
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
            String fieldName = (String) req.getAttribute("url.field");
            List<TableColumn> fields = airlineDAO.getTableColumns(table);
            for (TableColumn field : fields) {
                if (fieldName.equals(field.getName())) {
                    req.setAttribute("url.field", field);
                    return null;
                }
            }
            return MessageError.INEXISTANT_FIELD;
        }
    }

    private Table getTable(ServletRequest req) {
        String tableName = (String) req.getAttribute("url.table");
        Map<String, Table> tables = airlineDAO.getTables();
        return tables.get(tableName);
    }
}
