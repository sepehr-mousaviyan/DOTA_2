package sbu.cs.mahkats.Configuration;

import java.util.ResourceBundle;

public class InterfaceConfig extends Config {

    private InterfaceConfig() {
        config = ResourceBundle.getBundle("InterfaceConfig");
    }

    public static Config getInstance()
    {
        if (config_instance == null) {
            config_instance = new InterfaceConfig();
        }
        return config_instance;
    }
}
