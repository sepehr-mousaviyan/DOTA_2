package sbu.cs.mahkats.Server.Unit.Building.Tower;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Building.Building;

public class Tower extends Building {
    public Tower(String teamName, String lane , int choice , int code){
        super(teamName, lane, "Tower" , code);
        this.lane = lane;
        Config config = BuildingConfig.getInstance("TowerConfig");

        minimum_damage = config.getDoubleValue("tower.minimum.damage");
        maximum_damage = config.getDoubleValue("tower.maximum.damage");
        hp = config.getDoubleValue("tower.hp");
        armor = config.getDoubleValue("tower.armor");
        range = config.getDoubleValue("tower.range");
        hp_regeneration = config.getDoubleValue("tower.hp_regeneration");
        experience = config.getDoubleValue("tower.experience");

        Location_x = config.getIntValue("barrack.tower." + lane.toLowerCase() + "." + choice + ".location_x");
        Location_y = config.getIntValue("barrack.tower." + lane.toLowerCase() + "." + choice + ".location_y");

        if(teamName.equals("Red")){
            config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.heigth") - Location_y;
        }
    }
}
