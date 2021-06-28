package sbu.cs.mahkats.Server.Unit.Movable.Hero;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Movable.Movable;
import sbu.cs.mahkats.Server.Unit.Unit;

import java.util.ArrayList;

public class Hero extends Movable {
    protected String hero_name;

    protected Ability ability1;
    protected Ability ability2;
    protected Ability ability3;

    protected ArrayList<Ability> abilties;
    protected ArrayList<Unit> defenders;

    protected short level;

    protected double levelUp_benefit_hp;
    protected double levelUp_benefit_mana;
    protected double levelUp_benefit_damage;
    protected double levelUp_benefit_armor;
    protected double levelUp_benefit_hp_regeneration;
    protected double levelUp_benefit_mana_regeneration;
    protected int remainRespawnTime;
    protected boolean isLevelUp;
    protected boolean isRespawnTime;

    protected int LEVEL_NUMBERS;
    protected int[] LEVEL_XP;

    public Hero(String teamName , int code, String hero_name) {
        super(teamName ,"Hero" , code);
        isLevelUp = false;
        isRespawnTime = false;
        remainRespawnTime = 10;

        this.hero_name = hero_name;
        ability1 = new Ability(hero_name, 1);
        abilties.add(ability1);
        ability2 = new Ability(hero_name, 2);
        abilties.add(ability2);
        ability3 = new Ability(hero_name, 3);
        abilties.add(ability3);

        

        ability1.setUnlock();
        ability2.setCanUnlock();

        Config heroConfig = HeroConfig.getInstance(hero_name);

        LEVEL_NUMBERS = heroConfig.getIntValue("hero.level_numbers");
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

        Config levelUpConfig = HeroConfig.getInstance("Level_Xp");

        LEVEL_XP = new int[LEVEL_NUMBERS];
        for (int i = 0; i < LEVEL_NUMBERS; i++ ) {
            LEVEL_XP[i] = levelUpConfig.getIntValue("hero.xp_needed.level" + i);
        }
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
            if(!ability3.isMaxStage() || !ability2.isMaxStage() || !ability1.isMaxStage()) {
                isLevelUp = true;
            }
            if(ability3.getUNLOCK_LEVEL() == level){
                ability3.setCanUnlock();
            }
        }  
    }

    public boolean canLevelUp(){
        if(LEVEL_XP[level] <= experience){
            return true;
        }
        return false;
    }

    /**
     * upgrade ability but if can not do that return false
     * @param choice the number of ability
     * @return if can upgrade return true otherwise return false
     */
    public boolean upgradeAbility(int choice){
        if(isLevelUp){
            switch (choice){
                case 1:
                    if(!ability1.stageUp()){
                        return false;
                    }
                    break;
                case 2:
                    if(!ability2.stageUp()){
                        return false;
                    }
                    break;
                case 3:
                    if(!ability3.stageUp()){
                        return false;
                    }
                    break;
            }
            isLevelUp = false;
            return true;
        }
        return false;
    }

    public void respawnTime(){
        isRespawnTime = true;
        remainRespawnTime *= 1.9;
    }

    public void respawnAgain(){
        Location_x = 10;
        Location_y = 10;
        if(teamName.equals("RED")){
            Config config = InterfaceConfig.getInstance();
            Location_x = config.getIntValue("map.width") - Location_x;
            Location_y = config.getIntValue("map.height") - Location_y;
        }
    }

    public void takeDamage(double damage){
        if((damage - armor) > hp){
            hp = 0;
            isDie = false;
            this.respawnTime();
            return;
        }
        hp = hp - (damage - armor);
    }

    @Override
    public void takeDamage(double damage , Hero hero) {
        if((damage - armor) > hp){
            hp = 0;
            isDie = true;
            this.respawnTime();
            hero.addHeroExperience(this.experience);
            return;
        }
        hp = hp - (damage - armor);
    }

    public void addRegularExperience(double extraExperience) {
        experience += extraExperience;
    }

    public void addHeroExperience(double extraExperience) {
        experience += 100 + 0.13 * extraExperience;
    }

    public void addDefenders(Unit defender) {
        defenders.add(defender);
    }

    public int getLEVEL_NUMBERS() {
        return LEVEL_NUMBERS;
    }

    public int getLEVEL_XP() {
        return LEVEL_XP[level];
    }


    public ArrayList<Unit> getDefenders() {
        return defenders;
    }

    public int getRemainRespawnTime() {
        return remainRespawnTime;
    }

    public boolean isRespawnTime() {
        return isRespawnTime;
    }

    public ArrayList<Ability> getAbilities() {
        return abilties;
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
