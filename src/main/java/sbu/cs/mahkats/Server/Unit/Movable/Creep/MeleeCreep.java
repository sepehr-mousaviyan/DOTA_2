package sbu.cs.mahkats.Server.Unit.Movable.Creep;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.CreepConfig;

public class MeleeCreep extends Creep {
    public MeleeCreep(int Location_x, int Location_y, String lane, String teamName, int code) {
        super(lane, teamName, "MeleeCreep" , code);
        Config creepConfig = CreepConfig.getInstance();
        minimum_damage = creepConfig.getDoubleValue("creep.melee.minimum.damage");
        maximum_damage = creepConfig.getDoubleValue("creep.melee.maximum.damage");
        hp = creepConfig.getDoubleValue("creep.melee.hp");
        armor = creepConfig.getDoubleValue("creep.melee.armor");
        range = creepConfig.getDoubleValue("creep.melee.range");
        hp_regeneration = creepConfig.getDoubleValue("creep.melee.hp_regeneration");
        experience = creepConfig.getDoubleValue("creep.melee.experience");

        this.Location_x = Location_x;
        this.Location_y = Location_y;
    }
}
