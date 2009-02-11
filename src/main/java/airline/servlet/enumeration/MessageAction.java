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

package airline.servlet.enumeration;

/**
 * Les messages envoyables par la servlet a message.jsp
 */
public enum MessageAction {
    TABLE_DELETED, FIELD_DELETED, FIELD_EDITED, FIELD_ADDED, ROW_DELETED, ROW_ADDED, ROW_EDITED, TABLE_CREATED
}
