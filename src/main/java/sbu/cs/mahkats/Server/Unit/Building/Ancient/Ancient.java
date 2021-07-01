package sbu.cs.mahkats.Server.Unit.Building.Ancient;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Building.Building;

public class Ancient extends Building {
    public Ancient(String teamName, int code) {
        super(teamName, "Ancient", code);
        Config config = BuildingConfig.getInstance("AncientConfig");
        hp = config.getDoubleValue("ancient.hp");
        hp_regeneration = config.getDoubleValue("ancient.hp_regeneration");
        armor = config.getDoubleValue("ancient.armor");

        Location_x = config.getIntValue("ancient.location_x");
        Location_y = config.getIntValue("ancient.location_y");
        if (teamName.equals("RED")) {
            config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.height") - Location_y;
        }
    }
}
