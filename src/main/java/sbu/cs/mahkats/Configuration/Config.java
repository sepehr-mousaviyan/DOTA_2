package sbu.cs.mahkats.Configuration;

import java.util.ResourceBundle;

public class Config {
    protected static Config config_instance = null;
    protected ResourceBundle config;
    protected String bundle;
    protected Config() {
        this.bundle = "Config";
        config = ResourceBundle.getBundle("Config");
    }

    public static Config getInstance()
    {
        if (config_instance == null || !config_instance.getBundle().equals("Config")) {
            config_instance = new Config();
        }
        return config_instance;
    }

    public String getBundle() {
        return bundle;
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

    public double getDoubleValue(String key){
        return Double.parseDouble(config.getString(key));
    }

}
