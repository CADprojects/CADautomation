package helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrei.Ostrovski on 28.11.2016.
 */
public class PropertiesUtils {

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        try(InputStream is = PropertiesUtils.class.getResourceAsStream(path)) {
            properties.load(is);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return properties;
    }
}
