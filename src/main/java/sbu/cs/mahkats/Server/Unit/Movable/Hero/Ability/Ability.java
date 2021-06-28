package sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;

import java.util.ArrayList;

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

    /**
     * this function for using ability
     * @param hero that use this ability
     */
    public void use(Hero hero) {
        if(isAvailable){
            int lastTurn;
            double temp_damage;
            switch(this.NAME) {
                case "breathFire":
                    left_duration_turn = DURATION[stage];
                    lastTurn = GamePlay.getTurn();
                    while(left_duration_turn != 0) {
                        if(lastTurn < GamePlay.getTurn()) {
                            breathFire(hero);
                            left_duration_turn--;
                            lastTurn = GamePlay.getTurn();
                        }
                    }
                    break;
                case "dragonTail":
                    left_duration_turn = DURATION[stage];
                    lastTurn = GamePlay.getTurn();
                    while(left_duration_turn != 0) {
                        if(lastTurn < GamePlay.getTurn()) {
                            dragonTail(hero, hero.getDefender());
                            left_duration_turn--;
                            lastTurn = GamePlay.getTurn();
                        }
                    }
                    break;

                case "elderDragonForm":
                    left_duration_turn = DURATION[stage];
                    lastTurn = GamePlay.getTurn();
                    double temp_range = hero.getRange();
                    temp_damage = hero.getMinimum_damage();
                    hero.setRange(2);
                    hero.setMinimum_damage(this.getDamage());
                    while(left_duration_turn != 0) {
                        if(lastTurn < GamePlay.getTurn()) {
                            left_duration_turn--;
                        }
                    }
                    hero.setRange(temp_range);
                    hero.setMinimum_damage(temp_damage);
                    break;

                case "frostArrows":
                    left_duration_turn = DURATION[stage];
                    lastTurn = GamePlay.getTurn();
                    temp_damage = hero.getMinimum_damage();
                    hero.setMinimum_damage(this.getDamage());
                    while(left_duration_turn != 0) {
                        if(lastTurn < GamePlay.getTurn()) {
                            left_duration_turn--;
                        }
                    }
                    hero.setMinimum_damage(temp_damage);
                    break;
                    
                case "multiArrow":
                    left_duration_turn = DURATION[stage];
                    lastTurn = GamePlay.getTurn();
                    while(left_duration_turn != 0) {
                        if(lastTurn < GamePlay.getTurn()) {
                            multiArrow(hero);
                            left_duration_turn--;
                            lastTurn = GamePlay.getTurn();
                        }
                    }
                    break;
                    
                case "marksmanship":
                    if(left_duration_turn == 0) {
                        left_duration_turn = DURATION[stage];
                    }
                    marksmanship(hero);
                    left_duration_turn--;
                    if(left_duration_turn != 0){
                        return;
                    }
                    break;
            }
            isAvailable = false;
        }
        //count reload duration
        left_duration_reload_turn = RELOAD_DURATION[stage];
        int lastTurn = GamePlay.getTurn();
        while(left_duration_reload_turn != 0) {
            if(lastTurn < GamePlay.getTurn()) {
                left_duration_reload_turn--;
                lastTurn = GamePlay.getTurn();
            }
        }
        isAvailable = true;
    }

    public void start() {
        switch(this.NAME) {
            case "breathFire":
                breathFire();
                break;
        }
        return false;
        
    }

    public int getLeft_duration_turn(){ return left_duration_turn; }

    public void breathFire(Hero hero) {
        GamePlay.abilityHit(GUNSHOT, hero, this);
    }

    public void dragonTail(Hero hero, Unit defender) {
        defender.takeDamage(this.getDamage() , hero);
    }

    public void multiArrow(Hero hero) {
        ArrayList<Unit> defenders = hero.getDefenders();
        for(Unit defender : defenders){
            if(defender.getUnitType().equals("Hero")){
                ((Hero)defender).takeDamage(this.getDamage());
            }
            else defender.takeDamage(this.getDamage());
        }
    }

    public void marksmanship(Hero hero) {
        GamePlay.abilityHit(GUNSHOT, hero, this);
    }

    public void breathFire() {
        
    }

    public void setCanUnlock(){
        isUnlock = true;
    }

    public int getUNLOCK_LEVEL() {
        return UNLOCK_LEVEL;
    }

    public String getNAME() {
        return NAME;
    }

    public int getMANA_COST() {
        return MANA_COST[stage];
    }

    public boolean isMaxStage(){ return (stage >= STAGE_NUMBERS); }
}
