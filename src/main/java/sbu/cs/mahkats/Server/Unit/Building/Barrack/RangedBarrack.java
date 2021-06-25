package sbu.cs.mahkats.Server.Unit.Building.Barrack;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.RangedCreep;

import java.util.ArrayList;

public class RangedBarrack extends Barrack {
    private final ArrayList <RangedCreep> rangedCreeps = new ArrayList<>();
    public RangedBarrack(String lane, String teamName , int code) {
        super(lane, teamName , code);
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
    public void addCreep(ArrayList<RangedCreep> rangedCreep) {
        this.rangedCreeps.addAll(rangedCreep);
    }

    public void removeCreep(Creep creep){
        rangedCreeps.remove(creep);
        for(Creep c : rangedCreeps){
            c.reduce_hp(20);
            c.reduceDamage(3);
        }
    }
}
