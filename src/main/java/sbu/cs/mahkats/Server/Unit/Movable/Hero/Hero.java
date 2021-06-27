package sbu.cs.mahkats.Server.Unit.Movable.Hero;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Movable.Movable;

public class Hero extends Movable {


    protected String hero_name;

    protected Ability ability1;
    protected Ability ability2;
    protected Ability ability3;

    protected short level;

    protected double levelUp_benefit_hp;
    protected double levelUp_benefit_mana;
    protected double levelUp_benefit_damage;
    protected double levelUp_benefit_armor;
    protected double levelUp_benefit_hp_regeneration;
    protected double levelUp_benefit_mana_regeneration;

    protected int LEVEL_NUMBERS;
    protected int[] LEVEL_XP;

    public Hero(String teamName , int code, String hero_name) {
        super(teamName ,"Hero" , code);

        this.hero_name = hero_name;
        ability1 = new Ability(hero_name, 1);
        ability2 = new Ability(hero_name, 2);
        ability3 = new Ability(hero_name, 3);

        Config levelUpConfig = HeroConfig.getInstance("Level_Xp");
        
        LEVEL_NUMBERS = heroConfig.getIntValue("hero.level_numbers");
        LEVEL_XP = new int[LEVEL_NUMBERS];
        for (int i = 0; i < LEVEL_NUMBERS; i++ ) {
            LEVEL_XP[i] = levelUpConfig.getIntValue("hero.xp_needed.level" + i);
        }

        Config heroConfig = HeroConfig.getInstance(hero_name);
        hp = max_hp = heroConfig.getIntValue("hero." + hero_name + ".hp");
        mana = max_mana = heroConfig.getIntValue("hero." + hero_name + ".mana");
        minimum_damage = heroConfig.getIntValue("hero." + hero_name + ".minimum.damage");
        maximum_damage = heroConfig.getIntValue("hero." + hero_name + ".maximum.damage");
        armor = max_armor = heroConfig.getIntValue("hero." + hero_name + ".armor");
        range = heroConfig.getIntValue("hero." + hero_name + ".range");
        hp_regeneration = heroConfig.getIntValue("hero." + hero_name + ".hp_regeneration");
        mana_regeneration = heroConfig.getIntValue("hero." + hero_name + ".mana_regeneration");

        levelUp_benefit_hp = heroConfig.getDoubleValue("hero." + hero_name + ".levelUp_benefit.hp");
        levelUp_benefit_mana = heroConfig.getDoubleValue("hero." + hero_name + ".levelUp_benefit.mana");
        levelUp_benefit_damage = heroConfig.getDoubleValue("hero." + hero_name + ".levelUp_benefit.damage");
        levelUp_benefit_armor = heroConfig.getDoubleValue("hero." + hero_name + ".levelUp_benefit.armor");
        levelUp_benefit_hp_regeneration = heroConfig.getDoubleValue("hero." + hero_name + ".levelUp_benefit.hp_regeneration");
        levelUp_benefit_mana_regeneration = heroConfig.getDoubleValue("hero." + hero_name + ".levelUp_benefit.mana_regeneration");

    }

    public void levelUp() {
        if (level < 12) {
            level++;
            max_hp += levelUp_benefit_hp;
            max_mana += levelUp_benefit_mana;
            minimum_damage += levelUp_benefit_damage;
            maximum_damage += levelUp_benefit_damage;
            max_armor += levelUp_benefit_armor;
            hp_regeneration += levelUp_benefit_hp_regeneration;
            mana_regeneration += levelUp_benefit_mana_regeneration;
            GamePlay.heroLevelUp();
        }  
    }

    public boolean canLevelUp(){
        if(LEVEL_XP[level] <= experience){
            return true;
        }
        return false;
    }

    public Ability getLEVEL_NUMBERS() {
        return LEVEL_NUMBERS;
    }

    public Ability getLEVEL_XP() {
        return LEVEL_XP;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public Ability getAbility3() {
        return ability3;
    }

}
