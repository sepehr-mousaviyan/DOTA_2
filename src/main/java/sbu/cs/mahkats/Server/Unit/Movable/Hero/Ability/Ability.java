package sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;

public class Ability {
    private final String NAME;
    private final int UNLOCK_LEVEL;
    private final int GUNSHOT;
    private final int STAGE_NUMBERS;
    private final int RANGE;
    private final int [] DAMAGE;
    private final int [] MANA_COST;
    private final int [] RELOAD_DURATION;
    private final int [] DURATION;

    private int stage;

    public Ability(String hero_name, int ability_number) {
        
        stage = 0;
        
        Config heroConfig = HeroConfig.getInstance(hero_name);

        NAME = heroConfig.getStringValue("hero." + hero_name + ".ability" + ability_number +".name");
        UNLOCK_LEVEL = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".unlockLevel");
        GUNSHOT = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".gunshot");
        STAGE_NUMBERS = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".stage_numbers");
        RANGE = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".range");

        DAMAGE = new int[STAGE_NUMBERS];
        MANA_COST = new int[STAGE_NUMBERS];
        RELOAD_DURATION = new int[STAGE_NUMBERS];
        DURATION = new int[STAGE_NUMBERS];

        for (int i = 0; i < STAGE_NUMBERS; i++) {
            int stage_number = i + 1;
            DAMAGE[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".damage." + stage_number);
            MANA_COST[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".mana_cost." + stage_number);
            RELOAD_DURATION[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".reload_duration." + stage_number);
            DURATION[i] = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".duration." + stage_number);
        }


    }

    public void start() {
        switch(this.NAME) {
            case "breathFire":
                breathFire();
                break;
        }
    }

    public void breathFire() {
        
    }
}
