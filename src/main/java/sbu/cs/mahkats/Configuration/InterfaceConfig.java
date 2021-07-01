package sbu.cs.mahkats.Configuration;

import java.util.ResourceBundle;

public class InterfaceConfig extends Config {

    private InterfaceConfig() {
        this.bundle = "InterfaceConfig";
        config = ResourceBundle.getBundle("Config/InterfaceConfig");
    }

    public static Config getInstance()
    {
        if (config_instance == null || !config_instance.getBundle().equals("InterfaceConfig")) {
                config_instance = new InterfaceConfig();
        }
        return config_instance;
    }
}
