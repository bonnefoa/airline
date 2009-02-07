package airline;

import airline.connector.Connector;
import airline.guiceBindings.ModuleTestDAO;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;

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