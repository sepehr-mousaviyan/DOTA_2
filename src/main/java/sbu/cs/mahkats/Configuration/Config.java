package sbu.cs.mahkats.Configuration;

import java.util.ResourceBundle;

public class Config {
    private static Config config_instance = null;
    private final ResourceBundle config;

    private Config() {
        config = ResourceBundle.getBundle("config");
    }

    public static Config getInstance()
    {
        if (config_instance == null) {
            config_instance = new Config();
        }
        return config_instance;
    }

    public int getIntValue(String key) {
        return Integer.parseInt(config.getString(key));
    }

    public String getStringValue(String key) {
        return config.getString(key);
    }

    public boolean getBooleanValue(String key) {
        return config.getString(key).equalsIgnoreCase("true");
    }

}
