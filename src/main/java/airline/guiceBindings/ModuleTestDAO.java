package airline.guiceBindings;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.dao.AirlineDAO;
import airline.dao.AuthDAO;
import airline.dao.TransactionDAO;
import airline.dao.impl.AirlineDAOImpl;
import airline.dao.impl.AuthDAOImpl;
import airline.dao.impl.TransactionDAOImpl;
import airline.manager.AirlineManager;
import airline.manager.impl.AirlineManagerImpl;

/**
 * Module for test
 */
public class ModuleTestDAO extends AbstractModule {

    protected void configure() {
        bind(Connector.class).to(ConnectorTestImpl.class).in(Scopes.SINGLETON);
        bind(AirlineDAO.class).to(AirlineDAOImpl.class).in(Scopes.SINGLETON);
        bind(TransactionDAO.class).to(TransactionDAOImpl.class).in(Scopes.SINGLETON);
        bind(AirlineManager.class).to(AirlineManagerImpl.class).in(Scopes.SINGLETON);
        bind(AuthDAO.class).to(AuthDAOImpl.class).in(Scopes.SINGLETON);
    }
}
