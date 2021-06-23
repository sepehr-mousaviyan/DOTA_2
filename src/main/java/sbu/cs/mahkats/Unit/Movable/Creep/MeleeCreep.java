package sbu.cs.mahkats.Unit.Movable.Creep;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.CreepConfig;

public class MeleeCreep extends Creep {
    public MeleeCreep(int Location_x, int Location_y, String lane, String teamName) {
        super(lane, teamName);
        Config creepConfig = CreepConfig.getInstance();
        damage = creepConfig.getIntValue("creep.melee.damage");
        hp = creepConfig.getIntValue("creep.melee.hp");
        armor = creepConfig.getIntValue("creep.melee.armor");
        range = creepConfig.getIntValue("creep.melee.range");
        hp_regeneration = creepConfig.getIntValue("creep.melee.hp_regeneration");
        experience = creepConfig.getIntValue("creep.melee.experience");


        this.Location_x = Location_x;
        this.Location_y = Location_y;
    }
}
