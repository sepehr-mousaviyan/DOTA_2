package sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Server.Unit.Unit;

public class Ability {
    private final String NAME;
    private final int UNLOCK_LEVEL; //the level can this ability unlock
    private final int GUNSHOT;  //the number of unit can hit in one turn
    private final int STAGE_NUMBERS;    //maximum level
    private final int RANGE;
    private final double [] DAMAGE;     //the list of damage sort of level
    private final int [] MANA_COST;     //the list of mana that should cost for one use ability sort of level
    private final int [] RELOAD_DURATION;   //the list of turn that should stay for can use again ability sort of level
    private final int [] DURATION;      //the list of turn that duration a use ability sort of level
    
    private int left_duration_turn; //the count duration of ability for end
    private int left_duration_reload_turn;  //the count turn until unlock again ability to use
    private boolean isAvailable;    //if ability is not in reload is true
    private int reloadDuration;
    private int stage;      //level of ability
    private boolean isUnlock;   //if hero unlock this is true
    private boolean canUnlock;  // if hero can unlock this is true

    public Ability(String hero_name, int ability_number) {
        
        stage = 0;
        isUnlock = false;
        isAvailable = true;
        
        Config heroConfig = HeroConfig.getInstance(hero_name);

        NAME = heroConfig.getStringValue("hero." + hero_name + ".ability" + ability_number +".name");
        UNLOCK_LEVEL = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".unlockLevel");
        GUNSHOT = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".gunshot");
        STAGE_NUMBERS = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number +".stage_numbers");
        RANGE = heroConfig.getIntValue("hero." + hero_name + ".ability" + ability_number + ".range");

        DAMAGE = new double[STAGE_NUMBERS];
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

    /**
     * this function for using ability
     * @param hero that use this ability
     */
    public void use(Hero hero) {
        if(isAvailable){
            switch(this.NAME) {
                case "breathFire":
                    new Thread(()->{
                        left_duration_turn = DURATION[stage];
                        int lastTurn = GamePlay.getTurn();
                        while(left_duration_turn != 0) {
                            if(lastTurn < GamePlay.getTurn()) {
                            breathFire(hero);
                            left_duration_turn--;
                            lastTurn = GamePlay.getTurn();
                            }
                        }
                    });
                    break;
                case "dragonTail":
                    //TODO recieve from client
                    new Thread(()->{
                        left_duration_turn = DURATION[stage];
                        int lastTurn = GamePlay.getTurn();
                        while(left_duration_turn != 0) {
                            if(lastTurn < GamePlay.getTurn()) {
                                dragonTail(hero, hero.getDefender());
                                left_duration_turn--;
                                lastTurn = GamePlay.getTurn();
                            }
                        }
                    });
                    break;
                

                case "elderDragonForm":
                    //TODO
                    new Thread(()->{
                        left_duration_turn = DURATION[stage];
                        int lastTurn = GamePlay.getTurn();
                        while(left_duration_turn != 0) {
                            if(lastTurn < GamePlay.getTurn()) {
                                //elderDragonForm(hero);
                                left_duration_turn--;
                                lastTurn = GamePlay.getTurn();
                            }
                        }
                    });
                    break;

                case "frostArrows":
                    //TODO
                    break;
                    
                case "multiArrow":
                    new Thread(()->{
                        left_duration_turn = DURATION[stage];
                        int lastTurn = GamePlay.getTurn();
                        while(left_duration_turn != 0) {
                            if(lastTurn < GamePlay.getTurn()) {
                                multiArrow(hero);
                                left_duration_turn--;
                                lastTurn = GamePlay.getTurn();
                            }
                        }
                    });
                    break;
                    
                case "marksmanship":
                    new Thread(()->{
                        left_duration_turn = DURATION[stage];
                        int lastTurn = GamePlay.getTurn();
                        while(left_duration_turn != 0) {
                            if(lastTurn < GamePlay.getTurn()) {
                                marksmanship(hero);
                                left_duration_turn--;
                                lastTurn = GamePlay.getTurn();
                            }
                        }
                    });
                    //TODO
                    break;
            }
            isAvailable = false;
        }
        //count reload duration
        new Thread(()->{
            left_duration_reload_turn = RELOAD_DURATION[stage];
            int lastTurn = GamePlay.getTurn();
            while(left_duration_reload_turn != 0) {
                if(lastTurn < GamePlay.getTurn()) {
                    left_duration_reload_turn--;
                    lastTurn = GamePlay.getTurn();
                }
            }
            isAvailable = true;
        });
    }

    /**
     * if can level up ability do that and return true
     * @return can stage up or not
     */
    public boolean stageUp() {
        if (stage < STAGE_NUMBERS - 1) {
            stage++;
            return true;
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
        GamePlay.abilityHit(GUNSHOT, hero, this);
    }

    public void marksmanship(Hero hero) {
        GamePlay.abilityHit(GUNSHOT, hero, this);
    }

    public double getDamage(){
        return DAMAGE[stage];
    }
    
    public boolean getIsUnlock() {
        return isUnlock;
    }

    public int getRange(){ return RANGE; }

    public void levelUp(){
        stage++;
    }

    public void setUnlock(){
        isUnlock = true;
    }

    public void setCanUnlock(){
        isUnlock = true;
    }

    public int getUNLOCK_LEVEL() {
        return UNLOCK_LEVEL;
    }

    public boolean isMaxStage(){ return (stage >= STAGE_NUMBERS); }
}
