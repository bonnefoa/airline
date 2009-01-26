package airline;

import com.google.inject.Injector;
import com.google.inject.Guice;
import com.google.inject.AbstractModule;
import airline.guiceBindings.ModuleTestDAO;

/**
 * Base class for junit
 */
public class BaseClass {

    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(getModule());
        injector.injectMembers(this);
    }

    public AbstractModule getModule() {
        return new ModuleTestDAO();
    }
}