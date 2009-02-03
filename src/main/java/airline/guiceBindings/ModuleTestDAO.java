package airline.guiceBindings;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import airline.connector.Connector;
import airline.connector.impl.ConnectorTestImpl;
import airline.dao.AirlineDAO;
import airline.dao.impl.AirlineDAOImpl;

/**
 * Module for test
 */
public class ModuleTestDAO extends AbstractModule {

    protected void configure() {
        bind(Connector.class).to(ConnectorTestImpl.class).in(Scopes.SINGLETON);
        bind(AirlineDAO.class).to(AirlineDAOImpl.class).in(Scopes.SINGLETON);
    }
}