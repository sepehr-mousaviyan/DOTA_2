package sbu.cs.mahkats.Server.Unit.Building.Barrack;

import java.util.ArrayList;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.MeleeCreep;

public class MeleeBarrack extends Barrack {
    private final ArrayList<MeleeCreep> meleeCreeps = new ArrayList<>();
    public MeleeBarrack(String lane, String teamName , int code) {
        super(lane, teamName , code);
        Config config = BuildingConfig.getInstance("BarrackConfig");
        hp = config.getDoubleValue("barrack.melee.hp");
        hp_regeneration = config.getDoubleValue("barrack.melee.hp_regeneration");
        armor = config.getDoubleValue("barrack.melee.armor");
        experience = config.getDoubleValue("barrack.melee.experience");
        lane = lane.toLowerCase();
        Location_x = config.getIntValue("melee.barrack." + lane  + ".lane.x");
        Location_y = config.getIntValue("melee.barrack." + lane + ".lane.y");
        
        if(teamName.equals("Red")){
            config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.height") - Location_y;
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
