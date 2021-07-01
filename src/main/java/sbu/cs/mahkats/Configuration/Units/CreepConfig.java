package sbu.cs.mahkats.Configuration.Units;

import sbu.cs.mahkats.Configuration.Config;

import java.util.ResourceBundle;

public class CreepConfig extends Config {

    private CreepConfig() {
        this.bundle = "CreepConfig";
        config = ResourceBundle.getBundle("Config/UnitConfig/Movable/CreepConfig");
    }

    public static Config getInstance()
    {
        if (config_instance == null || !config_instance.getBundle().equals("CreepConfig")) {
            config_instance = new CreepConfig();
        }
        return config_instance;
    }
}
