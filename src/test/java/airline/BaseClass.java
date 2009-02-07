package airline;

import com.google.inject.Injector;
import com.google.inject.Guice;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import airline.guiceBindings.ModuleTestDAO;
import airline.model.Table;
import airline.connector.impl.ConnectorTestImpl;
import airline.connector.Connector;
import org.junit.Before;
import org.junit.After;

import java.util.Map;

/**
 * Base class for junit
 */
public class BaseClass {
    private Connector connector;

    public AbstractModule getModule() {
        return new ModuleTestDAO();
    }

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(getModule());
        injector.injectMembers(this);
        connector.initSchema();
        connector.fillTables();
    }

    @After
    public void tearDown() throws Exception {
        connector.dropTables();
    }


    @Inject
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

}