package sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;

public class Ability {
    String NAME;

    int UNLOCK_LEVEL;

    int GUNSHOT;

    int STAGE_NUMBERS;

    int RANGE;

    int [] DAMAGE;

    int [] MANA_COST;

    int [] RELOAD_DURATION;

    int [] DURATION;

    public Ability(String hero_name, int ability_number) {
        Config heroConfig = HeroConfig.getInstance(hero_name);

        NAME = heroConfig.getStringValue("hero." + hero_name + ".ability" + ability_number +".name");
        UNLOCK_LEVEL = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".unlockLevel");
        GUNSHOT = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".gunshot");
        STAGE_NUMBERS = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".stage_numbers");
        RANGE = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".range");
        for (int i = 0; i < STAGE_NUMBERS; i++) {
            int stage_number = i + 1;
            DAMAGE[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".damage." + stage_number);
            MANA_COST[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".mana_cost." + stage_number);
            RELOAD_DURATION[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".reload_duration." + stage_number);
            DURATION[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".duration." + stage_number);
        }


    }
}
