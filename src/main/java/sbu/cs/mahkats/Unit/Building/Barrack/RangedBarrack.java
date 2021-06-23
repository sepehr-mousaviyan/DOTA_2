package sbu.cs.mahkats.Unit.Building.Barrack;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;

public class RangedBarrack extends Barrack {
    public RangedBarrack(String lane, String teamName) {
        super(lane, teamName);
        Config config = BuildingConfig.getInstance("BarrackConfig");
        hp = config.getDoubleValue("barrack.ranged.hp ");
        hp_regeneration = config.getDoubleValue("barack.ranged.hp_regeneration");
        armor = config.getDoubleValue("barack.ranged.armor");
        experience = config.getDoubleValue("barack.ranged.experience");

        if(teamName.equals("GREEN")){
            switch (lane) {
                case "TOP":
                    Location_x++;
                    break;
                case "MIDDLE":
                    Location_x++;
                    Location_y--;
                    break;
                case "BOTTOM":
                    Location_y--;
                    break;
            }
        }
        else if(teamName.equals("RED")){
            switch (lane) {
                case "TOP":
                    Location_x--;
                    break;
                case "MIDDLE":
                    Location_x --;
                    Location_y ++;
                    break;
                case "BOTTOM":
                    Location_y ++;
                    break;
            }
        }
    }
}
