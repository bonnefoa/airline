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

package airline.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 11:31:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class FilterChainMock implements FilterChain {
    private boolean hasChained;

    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        hasChained = true;
    }

    public boolean hasChained() {
        return hasChained;
    }

    public void setHasChained(boolean hasChained) {
        this.hasChained = hasChained;
    }

    public void reset() {
        hasChained = false;
    }
}
