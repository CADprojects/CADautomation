package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Andrei.Ostrovski on 24.11.2016.
 */
public class DatabaseUtils {

    private static final Properties dbSettings;

    static {
        dbSettings = PropertiesUtils.getProperties("/dbConnection.properties");
    }

    public Connection connectToDB() {
        Connection connection = null;
        String connectionString = String.format("%s;user=%s;password=%s;database=%s",dbSettings.getProperty("db.URL"),dbSettings.getProperty("db.login"),dbSettings.getProperty("db.password"),dbSettings.getProperty("db.database"));
           try {
            return  connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void executeQuery(Connection connection) {

    }
}
