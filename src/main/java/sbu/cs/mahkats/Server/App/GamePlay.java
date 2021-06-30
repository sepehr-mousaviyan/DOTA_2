package sbu.cs.mahkats.Server.App;


import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Data.ActionHeroData;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Server.Connection.Client.Client;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Server.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Server.Unit.Building.Tower.TowerRunnable;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.CreepRunnable;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.MeleeCreep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.RangedCreep;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Server.Unit.Unit;
import sbu.cs.mahkats.Server.Unit.unitList;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class GamePlay {
    private static int REFRESH_RATE;           //the time of a turn
    private static int CREEP_GENERATE_TIME;    //the turn of spawn a group of creep 
    
    private static int RANGED_CREEP_NUMBERS;   //number of ranged creep in a spawn group
    private static int MELEE_CREEP_NUMBERS;    //number of melee creep in a spawn group

    private static int MAP_HEIGHT; //game map height
    private static int MAP_WIDTH;  //game map width
    private static int LANE_WIDTH; //width of the lanes ()
    
    private static unitList GreenUnits; // all the red units objects
    private static unitList RedUnits;  // all the green units objects

    private int CHUNK_SIZE; // size of unit moves

    private String teamName;

    private static int last_code = 1;
    private static boolean is_start = false;

    private final static Logger logger = Logger.getLogger(GamePlay.class.getName());

    private static int turn = 0;
    private int turn_nth_client = 0; 

    public GamePlay(String heroName1 , String heroName2) {
        Config config = InterfaceConfig.getInstance();
        MAP_HEIGHT  = config.getIntValue("map.height");
        MAP_WIDTH   = config.getIntValue("map.width");
        LANE_WIDTH  = config.getIntValue("map.lane.width");
        CHUNK_SIZE = config.getIntValue("game.chunk.size");

        config = Config.getInstance();
        REFRESH_RATE         = config.getIntValue("game.refreshRate");
        CREEP_GENERATE_TIME  = config.getIntValue("game.creep.generateTime");
        RANGED_CREEP_NUMBERS = config.getIntValue("game.creep.ranged.numbers");
        MELEE_CREEP_NUMBERS  = config.getIntValue("game.creep.melee.numbers");

        GreenUnits = new unitList("GREEN" , heroName1);
        RedUnits   = new unitList("RED" , heroName2);
    }

    public void play(ArrayList<Client> clients) {
        is_start = true;
        int lastTurnCreepSpawn = turn;
        ArrayList<Tower> towers = new ArrayList<>(GreenUnits.getTowers());
        towers.addAll(RedUnits.getTowers());
        TowerRunnable towerRunnable = new TowerRunnable(towers);
        new Thread(towerRunnable).start();
        int lastTurn = turn;

        logger.info("game start");
        while(true) {
            try {
                Thread.sleep(REFRESH_RATE);
                lastTurn = turn;
                turn ++;
            } catch (InterruptedException e) {
                logger.fatal("can not go next turn" , e);
            }
            if (turn - lastTurn >= 1) {
                recieveHeroData(clients);
                checkMap();
                hpRegenerateAll();
                manaRegenerateAll();

                //check end game
                if(GreenUnits.getAncient().isDie()){
                    endGame("RED", clients);
                    logger.info("Green is lost");
                    break;
                }
                if(RedUnits.getAncient().isDie()){
                    endGame("GREEN", clients);
                    logger.info("Red is lost");
                    break;
                }
                if (turn - lastTurnCreepSpawn == 60) {
                    spawnCreep();
                    lastTurnCreepSpawn = turn;
                }
                for(Client client : clients) {
                    client.sendData();
                    logger.info("send data");
                }
            }
        }
    }

    private void endGame(String name, ArrayList<Client> clients) {
        for(Client client : clients) {
            client.send(new MassageMaker().massage("EndGame", name).toString());
        }
    }

    /**
     * determine the lane of number
     * @param choice
     * @return name of lane
     */
    private String whichLane(int choice){
        switch (choice) {
            case 1:
                return "BOTTOM";
            case 2:
                return "MIDDLE";
            case 3:
                return "TOP";
            default:
                return null;
        }
    }

    /**
     * check the map if some one can attack other
     */
    public void checkMap() {
        ArrayList<Unit> greenUnits = GreenUnits.getAll();
        ArrayList<Unit> redUnits = RedUnits.getAll();

        ArrayList<Creep> greenCreeps = GreenUnits.getCreeps();
        ArrayList<Creep> redCreeps = RedUnits.getCreeps();

        ArrayList<Tower> greenTowers = GreenUnits.getTowers();
        ArrayList<Tower> redTowers = RedUnits.getTowers();

        ArrayList<Hero> greenHeroes = GreenUnits.getHeroes();
        ArrayList<Hero> redHeroes = RedUnits.getHeroes();

        ArrayList<Barrack> greenBarracks = GreenUnits.getBarracks();
        ArrayList<Barrack> redBarracks = RedUnits.getBarracks();
        
        //attacker: green
        main: for (Unit green: greenUnits){
            if(green.canHit(RedUnits.getAncient())){
                green.setDefender(RedUnits.getAncient());
                green.setStatusAttacker(true);
                continue;
            }
            //creeps
            for(Creep redC : redCreeps){
                if (green.canHit(redC)) {
                    green.setDefender(redC);
                    green.setStatusAttacker(true);
                    continue main;
                }
            }
            //towers
            for(Tower redT : redTowers) {
                if (green.canHit(redT)) {
                    green.setDefender(redT);
                    green.setStatusAttacker(true);
                    continue main;
                }
            }
            //heroes
            for(Hero redH : redHeroes) {
                if (green.canHit(redH)) {
                    green.setDefender(redH);
                    green.setStatusAttacker(true);
                    continue main;
                }
            }
            //barracks
            for(Barrack redB : redBarracks) {
                if (green.canHit(redB)) {
                    green.setDefender(redB);
                    green.setStatusAttacker(true);
                    continue main;
                }
            }
        }

        //attacker: red
        main: for (Unit red: redUnits){
            //ancient
            if (red.canHit(GreenUnits.getAncient())) {
                    red.setDefender(GreenUnits.getAncient());
                    red.setStatusAttacker(true);
                    continue main;
                }
            //creeps
            for(Creep greenC : greenCreeps) {
                if (red.canHit(greenC)) {
                    red.setDefender(greenC);
                    red.setStatusAttacker(true);
                    continue main;
                }
            }
            //towers
            for(Tower greenT : greenTowers) {
                if (red.canHit(greenT)) {
                    red.setDefender(greenT);
                    red.setStatusAttacker(true);
                    continue main;
                }
            }
            //heroes
            for(Hero greenH : greenHeroes) {
                if (red.canHit(greenH)) {
                    red.setDefender(greenH);
                    red.setStatusAttacker(true);
                    continue main;
                }
            }
            //barracks
            for(Barrack greenB : greenBarracks) {
                if (red.canHit(greenB)) {
                    red.setDefender(greenB);
                    red.setStatusAttacker(true);
                    continue main;
                }
            }
        }
    }

    public static void abilityHit(int gunshot, Hero hero, Ability ability) {
        int shots = 0;
        ArrayList<Unit> greenUnits = GreenUnits.getAll();
        ArrayList<Unit> redUnits = RedUnits.getAll();
        for (Unit greenU : greenUnits) {
            if (gunshot != 0 && hero.canHit(greenU, ability)) {
                if(shots >= gunshot) {
                    break;
                }
                shots++;
                greenU.takeDamage(ability.getDamage() , hero);
            }
            else if (gunshot == 0 && hero.canHit(greenU, ability)) {
                greenU.takeDamage(ability.getDamage() , hero);
            }
        }
        for (Unit redU : redUnits) {
            if (gunshot != 0 && hero.canHit(redU, ability)) {
                if(shots >= gunshot) {
                    break;
                }
                shots++;
                redU.takeDamage(ability.getDamage() , hero);
            }
            else if (gunshot == 0 && hero.canHit(redU, ability)) {
                redU.takeDamage(ability.getDamage() , hero);
            }
        }
    }

    public void recieveHeroData(ArrayList<Client> clients) {
        turn_nth_client++;
        if(turn_nth_client > 1){
            turn_nth_client = 0;
        }
        Client client = clients.get(turn_nth_client);
        client.send(new MassageMaker().massage("ok", "startTurn").toString());
        String data = client.receiveData();
        ActionHeroData actionHeroData = Parser.parseActionHeroData(new Api().toJson(data));
        Hero hero = GreenUnits.getHero(actionHeroData.getHeroCode()); 
        if (hero == null){
            hero = RedUnits.getHero(actionHeroData.getHeroCode());
        }
        teamName = actionHeroData.getTeamName();
        Hero thisHero = null;
        if (teamName.equals("GREEN")){
            thisHero = GreenUnits.getHeroes().get(0);
        } else if (teamName.equals("RED")){
            thisHero = RedUnits.getHeroes().get(0);
        } else {
            logger.fatal("wrong teamName.");
        }
        ArrayList<Ability> abilitis;
        switch (actionHeroData.getChoice()){
            //move hero
            case 1:
                thisHero.move(actionHeroData.getMove(), thisHero, CHUNK_SIZE);
                break;

            //add new ability
            case 2:
                abilitis= hero.getAbilities();
                for(Ability ability : abilitis) {
                    if (ability.getNAME().equals(actionHeroData.getAbilityName())) {
                        if(!ability.setUnlock()) {
                            //TODO
                        } 
                    }
                }
                break;

            //update ability
            case 3:
                abilitis= hero.getAbilities();
                for(Ability ability : abilitis) {
                    if (ability.getNAME().equals(actionHeroData.getAbilityName())) {
                        if(!ability.stageUp()) {
                            //TODO
                        }
                    }
                }
                break;

            //attack the unit
            case 4:
                Unit unit = GreenUnits.getUnit(actionHeroData.getHeroCode());
                if(unit == null){
                    unit = RedUnits.getUnit(actionHeroData.getHeroCode());
                }
    
                unit.takeDamage(hero.getDamage());
                break;

            //use ability
            case 6:
                Unit defender = GreenUnits.getUnit(actionHeroData.getDefenderCode());
                if(defender == null){
                    defender = RedUnits.getUnit(actionHeroData.getDefenderCode());
                }
                abilitis = hero.getAbilities();
                for(Ability ability : abilitis) {
                    if (ability.getNAME().equals(actionHeroData.getAbilityName())) {
                        int[] codes = actionHeroData.getDefendersCode();
                        if(actionHeroData.getDefenderCode() != 0){
                            hero.setDefender(defender);
                        }
                        else if(codes != null){
                            for (int code: codes) {
                                Unit defender_unit = GreenUnits.getUnit(code);
                                if(defender_unit == null){
                                    defender_unit = RedUnits.getUnit(code);
                                }
                                hero.addDefenders(defender_unit);
                            }
                        }
                        if(hero.reduceMana(ability.getMANA_COST())) {
                            Hero finalHero = hero;
                            new Thread(() -> ability.use(finalHero)).start();
                        }
                    }
                }
        }
    }


    /**
     * this function spawn a group of creep from a random barracks
     */
    public void spawnCreep(){
        String greenLane  = whichLane((int) (Math.random() * GreenUnits.getTowers().size()) + 1);
        String redLane  = whichLane((int) (Math.random() * RedUnits.getTowers().size()) + 1);
        ArrayList<MeleeCreep> redMeleeCreep = new ArrayList<>(); 
        ArrayList<MeleeCreep> greenMeleeCreep = new ArrayList<>(); 
        ArrayList<RangedCreep> redRangedCreep = new ArrayList<>();
        ArrayList<RangedCreep> greenRangedCreep = new ArrayList<>(); 
        

        for (int i = 0; i < RANGED_CREEP_NUMBERS; i++) {
            greenRangedCreep.add(new RangedCreep(GreenUnits.getRangedBarrack(greenLane).getLocation_x(),
                                        GreenUnits.getRangedBarrack(greenLane).getLocation_y(),
                                        greenLane, "GREEN" , last_code));
            addCode();
            
            redRangedCreep.add(new RangedCreep(RedUnits.getRangedBarrack(redLane).getLocation_x(),
                                        RedUnits.getRangedBarrack(redLane).getLocation_y(),
                                        greenLane, "RED" , last_code));
            addCode();
        }
        GreenUnits.addCreeps(new ArrayList<>(greenRangedCreep));
        GreenUnits.getRangedBarrack(greenLane).addCreep(greenRangedCreep);
        RedUnits.addCreeps(new ArrayList<>(redRangedCreep));
        RedUnits.getRangedBarrack(redLane).addCreep(redRangedCreep);

        for (int i = 0; i < MELEE_CREEP_NUMBERS; i++) {
            greenMeleeCreep.add(new MeleeCreep(GreenUnits.getMeleeBarracks(greenLane).getLocation_x(),
                                        GreenUnits.getMeleeBarracks(greenLane).getLocation_y(),
                                        greenLane, "GREEN" , last_code));
            addCode();

            redMeleeCreep.add(new MeleeCreep(RedUnits.getMeleeBarracks(redLane).getLocation_x(),
                                        RedUnits.getMeleeBarracks(redLane).getLocation_y(),
                                        greenLane, "RED" , last_code));
            addCode();
            RedUnits.addCreeps(new ArrayList<>(redMeleeCreep));
        }
        GreenUnits.addCreeps(new ArrayList<>(greenMeleeCreep));
        GreenUnits.getMeleeBarracks(greenLane).addCreep(greenMeleeCreep);
        RedUnits.addCreeps(new ArrayList<>(redMeleeCreep));
        RedUnits.getMeleeBarracks(redLane).addCreep(redMeleeCreep);

        CreepRunnable greenCreepRunnable = new CreepRunnable(GreenUnits.getCreeps());
        new Thread(greenCreepRunnable).start();
        CreepRunnable redCreepRunnable = new CreepRunnable(RedUnits.getCreeps());
        new Thread(redCreepRunnable).start();
        logger.info("spawn a creep for green");
        logger.info("spawn a creep for red");
    }

    /**
     * regenerate all hp of units
     */
    public void hpRegenerateAll(){
        ArrayList<Unit> units = GreenUnits.getAll();
        units.addAll(RedUnits.getAll());
        for(Unit unit : units){
            unit.hp_regenerate();
        }
    }

    public void manaRegenerateAll(){
        ArrayList<Hero> heroes = GreenUnits.getHeroes();
        heroes.addAll(RedUnits.getHeroes());
        for(Hero hero : heroes){
            hero.mana_regeneration();
        }
    }

    public static void destroy(Unit unit){
        if(unit.getTeamName().equals("GREEN")){
            GreenUnits.remove(unit);
            logger.info("a " + unit.getUnitType() + " is destroyed!");
            return;
        }
        RedUnits.remove(unit);
        logger.info("a " + unit.getUnitType() + " is destroyed!");
    }
    
    public static int getREFRESH_RATE() {
        return REFRESH_RATE;
    }

    public static int getCREEP_GENERATE_TIME() {
        return CREEP_GENERATE_TIME;
    }

    public static int getRANGED_CREEP_NUMBERS() {
        return RANGED_CREEP_NUMBERS;
    }

    public static int getMELEE_CREEP_NUMBERS() {
        return MELEE_CREEP_NUMBERS;
    }

    public static int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public static int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public static int getLANE_WIDTH() {
        return LANE_WIDTH;
    }

    public static int getTurn() {
        return turn;
    }

    public static int getLast_code(){ return last_code; }

    public static void addCode(){ last_code++; }

    public static int get_add_Code(){ return last_code++; }

    public static unitList getGreenUnits() {
        return GreenUnits;
    }

    public static unitList getRedUnits() {
        return RedUnits;
    }

    public static boolean isStarted(){ return is_start; }
}
