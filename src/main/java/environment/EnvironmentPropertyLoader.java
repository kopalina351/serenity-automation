package environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentPropertyLoader {
    public static String cfgFile = "src/config.properties";

    public static String getValueFromFile(String envName, String fileName) throws IOException {
        Properties properties = new Properties();
        FileInputStream cfg = new FileInputStream(fileName);
        properties.load(cfg);
        cfg.close();
        return (properties.getProperty(envName));
    }

    public static String getProperties(String key) throws IOException {

        return (getValueFromFile(key, cfgFile));
    }
}


