package sbu.cs.mahkats.Server.Unit.Building.Barrack;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.RangedCreep;

import java.util.ArrayList;

public class RangedBarrack extends Barrack {
    private final ArrayList<RangedCreep> rangedCreeps = new ArrayList<>();

    public RangedBarrack(String lane, String teamName, int code) {
        super(lane, teamName, code);
        Config config = BuildingConfig.getInstance("BarrackConfig");
        hp = config.getDoubleValue("barrack.ranged.hp");
        hp_regeneration = config.getDoubleValue("barrack.ranged.hp_regeneration");
        armor = config.getDoubleValue("barrack.ranged.armor");
        experience = config.getDoubleValue("barrack.ranged.experience");
        lane = lane.toLowerCase();
        Location_x = config.getIntValue("ranged.barrack." + lane + ".lane.x");
        Location_y = config.getIntValue("ranged.barrack." + lane + ".lane.y");

        if (teamName.equals("Red")) {
            config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.height") - Location_y;
        }
    }

    public void addCreep(ArrayList<RangedCreep> rangedCreep) {
        this.rangedCreeps.addAll(rangedCreep);
    }

    public void removeCreep(Creep creep) {
        rangedCreeps.remove(creep);
        for (Creep c : rangedCreeps) {
            c.reduce_hp(20);
            c.reduceDamage(3);
        }
    }
}
