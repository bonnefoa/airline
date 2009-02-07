/*
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 20:04:31
 */
package airline.guiceBindings;

import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.dao.AirlineDAO;
import airline.dao.AuthDAO;
import airline.dao.impl.AirlineDAOImpl;
import airline.dao.impl.AuthDAOImpl;
import airline.manager.RequestManager;
import airline.manager.impl.RowManagerImpl;
import airline.manager.impl.TableManagerImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class Servlet extends AbstractModule {
    protected void configure() {
        bind(Connector.class).to(ConnectorTestImpl.class).in(Scopes.SINGLETON);
        bind(AirlineDAO.class).to(AirlineDAOImpl.class).in(Scopes.SINGLETON);
        bind(AuthDAO.class).to(AuthDAOImpl.class).in(Scopes.SINGLETON);
        bind(RequestManager.class).annotatedWith(Names.named("Table")).to(TableManagerImpl.class);
        bind(RequestManager.class).annotatedWith(Names.named("Row")).to(RowManagerImpl.class);
    }
}
