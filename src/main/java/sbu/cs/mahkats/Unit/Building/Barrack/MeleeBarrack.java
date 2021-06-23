package sbu.cs.mahkats.Unit.Building.Barrack;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;

public class MeleeBarrack extends Barrack {
    public MeleeBarrack(String lane, String teamName) {
        super(lane, teamName);
        Config config = BuildingConfig.getInstance("BarrackConfig");
        hp = config.getDoubleValue("barrack.melee.hp");
        hp_regeneration = config.getDoubleValue("barack.melee.hp_regeneration");
        armor = config.getDoubleValue("barack.melee.armor");
        experience = config.getDoubleValue("barack.melee.experience");
        
        if(teamName.equals("GREEN")){
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
        else if(teamName.equals("Red")){
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
    }
}
