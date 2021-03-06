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

package airline.criteria.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Differents type of request.
 * Used for transaction
 */
public enum TypeRequest {
    SELECT("SELECT", 0), INSERT("INSERT", 1), UPDATE("UPDATE", 2), DELETE("DELETE", 3), CREATE_TABLE("CREATE TABLE", 4), DROP_TABLE("DROP TABLE", 5);

    private String sqlValue;

    private int typeRequest;

    private static Map<Integer, TypeRequest> lookup;

    static {
        lookup = new HashMap<Integer, TypeRequest>();
        for (TypeRequest typeRequest1 : EnumSet.allOf(TypeRequest.class)) {
            lookup.put(typeRequest1.getTypeRequest(), typeRequest1);
        }

    }

    TypeRequest(String sqlValue, int typeRequest) {
        this.sqlValue = sqlValue;
        this.typeRequest = typeRequest;
    }

    public String getSqlValue() {
        return sqlValue;
    }

    public int getTypeRequest() {
        return typeRequest;
    }

    public static TypeRequest lookupTypeRequest(int type) {
        if (lookup.containsKey(type)) {
            return lookup.get(type);
        }
        return null;
    }
}
