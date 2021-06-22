package sbu.cs.mahkats.Unit.Movable.Creep;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.CreepConfig;

public class Ranged extends Creep{
    public Ranged(int Location_x, int Location_y) {
        Config creepConfig = CreepConfig.getInstance();
        damage = creepConfig.getIntValue("creep.ranged.damage");
        hp = creepConfig.getIntValue("creep.ranged.hp");
        armor = creepConfig.getIntValue("creep.ranged.armor");
        range = creepConfig.getIntValue("creep.ranged.range");
        hp_regeneration = creepConfig.getIntValue("creep.ranged.hp_regeneration");
        experience = creepConfig.getIntValue("creep.ranged.experience");


        this.Location_x = Location_x;
        this.Location_y = Location_y;
    }
}
