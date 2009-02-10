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

/**
 * User: sora
 * Date: 31 janv. 2009
 * Time: 11:45:28
 */
public enum SqlConstraints {
    GT(">"), GE(">="),
    LT("<"), LE("<="),
    EQ("="), NEQ("!="),
    LIKE("like"), ILIKE("ilike");

    private String sqlValue;

    SqlConstraints(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public String getSqlValue() {
        return sqlValue;
    }
}
