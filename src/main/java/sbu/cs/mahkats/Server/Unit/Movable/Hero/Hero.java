package sbu.cs.mahkats.Server.Unit.Movable.Hero;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.Unit.Movable.Movable;

public class Hero extends Movable {
<<<<<<< HEAD
    String ability1;
    String ability2;
    String ability3;
    String ability4;

    

=======
    String hero_name;
>>>>>>> implement ability class

    Ability ability1;
    Ability ability2;
    Ability ability3;

    double levelUp_benefit_hp;
    double levelUp_benefit_mana;
    double levelUp_benefit_damage;
    double levelUp_benefit_armor;
    double levelUp_benefit_hp_regeneration;
    double levelUp_benefit_mana_regeneration;

    public Hero(String teamName , int code, String hero_name) {
        super(teamName ,"Hero" , code);

        this.hero_name = hero_name;
        ability1 = new Ability(hero_name, 1);
        ability2 = new Ability(hero_name, 2);
        ability3 = new Ability(hero_name, 3);

        Config heroConfig = HeroConfig.getInstance("Knight");
        hp = heroConfig.getIntValue("hero." + hero_name + ".hp");
        mana = heroConfig.getIntValue("hero." + hero_name + ".mana");
        minimum_damage = heroConfig.getIntValue("hero." + hero_name + ".minimum.damage");
        maximum_damage = heroConfig.getIntValue("hero." + hero_name + ".maximum.damage");
        armor = heroConfig.getIntValue("hero." + hero_name + ".armor");
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

    public Ability getAbility1() {
        return ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public Ability getAbility3() {
        return ability3;
    }

<<<<<<< HEAD
    public void setAbility3(Ability ability3) {
        this.ability3 = ability3;
    }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public String getAbility3() {
        return ability3;
    }

    public String getAbility4() {
        return ability4;
    }
    
=======
>>>>>>> load ranged and knight heroes configs
}
