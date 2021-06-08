package sbu.cs.mahkats.Configuration;

import java.util.ResourceBundle;

public class Config {
    protected static Config config_instance = null;
    protected ResourceBundle config;

    protected Config() {
        config = ResourceBundle.getBundle("Config");
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
