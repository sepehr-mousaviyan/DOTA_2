package sbu.cs.mahkats.Configuration;

import java.util.ResourceBundle;

public class SecurityConfig extends Config{

    private SecurityConfig() {
        config = ResourceBundle.getBundle("SecurityConfig");
    }

    public static Config getInstance()
    {
        if (config_instance == null) {
            config_instance = new SecurityConfig();
        }
        return config_instance;
    }
}
