package sbu.cs.mahkats.Server.Unit.Building.Barrack;

import java.util.ArrayList;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.MeleeCreep;

public class MeleeBarrack extends Barrack {
    private final ArrayList<MeleeCreep> meleeCreeps = new ArrayList<>();
    public MeleeBarrack(String lane, String teamName , int code) {
        super(lane, teamName , code);
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

    public void addCreep(ArrayList<MeleeCreep> meleeCreep) {
        this.meleeCreeps.addAll(meleeCreep);
    }

    public void removeCreep(Creep creep){
        meleeCreeps.remove(creep);
        for(Creep c : meleeCreeps){
            c.reduce_hp(20);
            c.reduceDamage(3);
        }
    }
}
