package sbu.cs.mahkats.Configuration.Units;

import sbu.cs.mahkats.Configuration.Config;

import java.util.ResourceBundle;

public class BuildingConfig extends Config {
    private static String building;

    private BuildingConfig(String building_name) {
        this.bundle = "Building";
        building = building_name;
        config = ResourceBundle.getBundle("Config/UnitConfig/Buildings/" + building);
    }

    public static Config getInstance(String building_name)
    {
        if (config_instance == null ||
                !config_instance.getBundle().equals("Building") ||
                !building.equals(building_name)) {
            config_instance = new BuildingConfig(building_name);
        }
        return config_instance;
    }
}
