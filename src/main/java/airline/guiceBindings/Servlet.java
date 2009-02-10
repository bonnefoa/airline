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

/*
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 20:04:31
 */
package airline.guiceBindings;

import airline.connector.Connector;
import airline.connector.impl.ConnectorImpl;
import airline.dao.AirlineDAO;
import airline.dao.AuthDAO;
import airline.dao.TransactionDAO;
import airline.dao.impl.AirlineDAOImpl;
import airline.dao.impl.AuthDAOImpl;
import airline.dao.impl.TransactionDAOImpl;
import airline.manager.AirlineManager;
import airline.manager.impl.AirlineManagerImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class Servlet extends AbstractModule {
    protected void configure() {
        bind(Connector.class).to(ConnectorImpl.class).in(Scopes.SINGLETON);
        bind(AirlineDAO.class).to(AirlineDAOImpl.class).in(Scopes.SINGLETON);
        bind(TransactionDAO.class).to(TransactionDAOImpl.class).in(Scopes.SINGLETON);
        bind(AirlineManager.class).to(AirlineManagerImpl.class).in(Scopes.SINGLETON);
        bind(AuthDAO.class).to(AuthDAOImpl.class).in(Scopes.SINGLETON);
    }
}
