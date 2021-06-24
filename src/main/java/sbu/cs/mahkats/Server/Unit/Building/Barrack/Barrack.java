package sbu.cs.mahkats.Server.Unit.Building.Barrack;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Building.Building;

public class Barrack extends Building {
    public Barrack(String lane, String teamName) {
        super(teamName, "Barrack");
        this.lane = lane;
        Config config = BuildingConfig.getInstance("BarrackConfig");
        switch (lane) {
            case "TOP":
                Location_x = config.getIntValue("barrack.barrack.topLane.location_x");
                Location_y = config.getIntValue("barrack.barrack.topLane.location_y");
                break;
            case "MIDDLE":
                Location_x = config.getIntValue("barrack.barrack.middlelane.location_x");
                Location_y = config.getIntValue("barrack.barrack.middlelane.location_y");
                break;
            case "BOTTOM":
                Location_x = config.getIntValue("barrack.barrack.bottomlane.location_x");
                Location_y = config.getIntValue("barrack.barrack.bottomlane.location_y");
                break;
        }
        if(teamName.equals("Red")){
            config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.heigth") - Location_y;
        }
    }
}
