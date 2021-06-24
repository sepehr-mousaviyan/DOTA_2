package sbu.cs.mahkats.Server.Unit.Movable.Creep;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.CreepConfig;

public class RangedCreep extends Creep{
    public RangedCreep(int Location_x, int Location_y, String lane, String teamName) {
        super(lane , teamName, "RangedCreep");
        Config creepConfig = CreepConfig.getInstance();
        minimum_damage = creepConfig.getDoubleValue("creep.ranged.minimum.damage");
        maximum_damage = creepConfig.getDoubleValue("creep.ranged.maximum.damage");
        hp = creepConfig.getDoubleValue("creep.ranged.hp");
        armor = creepConfig.getDoubleValue("creep.ranged.armor");
        range = creepConfig.getDoubleValue("creep.ranged.range");
        hp_regeneration = creepConfig.getDoubleValue("creep.ranged.hp_regeneration");
        experience = creepConfig.getDoubleValue("creep.ranged.experience");


        this.Location_x = Location_x;
        this.Location_y = Location_y;
    }
}
