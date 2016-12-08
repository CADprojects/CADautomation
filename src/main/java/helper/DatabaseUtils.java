package helper;

import java.sql.*;
import java.util.*;

/**
 * Created by Andrei.Ostrovski on 24.11.2016.
 */
public class DatabaseUtils {

    private static final Properties dbSettings;

    static {
        dbSettings = PropertiesUtils.getProperties("/dbSettings.properties");
    }

    public static Connection connectToDB() {
        Connection connection = null;
        String connectionString = String.format("%s;user=%s;password=%s;database=%s",dbSettings.getProperty("db.URL"),dbSettings.getProperty("db.login"),dbSettings.getProperty("db.password"),dbSettings.getProperty("db.database"));
           try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
               System.out.println( "SQL Exception:" +ex.getMessage());
        }
        return connection;
    }

    public static List<List<String>> getInfoFromDB(Connection conn, String spName) {
        List<List<String>> resultSet = new ArrayList<>();
        String query = String.format("{call %s}", dbSettings.getProperty(spName));
        try {
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {
                List<String> row = new ArrayList<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    if (rs.getString(i) == null) {
                        row.add("0.00");
                    } else {
                        row.add(rs.getString(i).replaceAll(",", ""));
                    }
                }
                resultSet.add(row);
            }
        } catch (SQLException ex) {
            System.out.println( "SQL Exception:" +ex.getMessage());
        }
        closeDBConnection(conn);
        return resultSet;
    }

    private static void closeDBConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println( "SQL Exception:" +ex.getMessage());
        }
    }
}
