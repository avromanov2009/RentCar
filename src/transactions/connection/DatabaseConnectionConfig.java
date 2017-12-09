package transactions.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by andrei on 26.11.17.
 */
public final class DatabaseConnectionConfig {
    private static final String PROPERTIES_FILE = "resources/db-config.properties";
    private static final Properties PROPERTIES = new Properties();
    private static Connection con = null;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        try {
            PROPERTIES.load(propertiesFile);
            classLoader.loadClass(PROPERTIES.getProperty("Database.Driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DatabaseConnectionConfig()
    {
    }
}
