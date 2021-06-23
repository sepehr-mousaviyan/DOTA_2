package sbu.cs.mahkats.Unit.Building.Tower;

import sbu.cs.mahkats.Unit.Building.Building;

public class Tower extends Building {
    public Tower(String teamName, String lane , int choice){
        super(teamName, lane);
        this.lane = lane;
        Config config = BuildingConfig.getInstance("TowerConfig");

        Location_x = config.getIntValue("barrack.tower." + lane.toLowerCase() + "." + choice + ".location_x");
        Location_y = config.getIntValue("barrack.tower." + lane.toLowerCase() + "." + choice + ".location_y");

        if(teamName.equals("Red")){
            config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.heigth") - Location_y;
        }
    }
}
