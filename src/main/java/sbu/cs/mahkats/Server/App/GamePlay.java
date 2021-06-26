package sbu.cs.mahkats.Server.App;


import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Server.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Server.Unit.Building.Tower.TowerRunnable;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.CreepRunnable;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.MeleeCreep;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.RangedCreep;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Server.Unit.Unit;
import sbu.cs.mahkats.Server.Unit.unitList;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.Barrack;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

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

    private static int last_code = 1;

    private final static Logger logger = Logger.getLogger(GamePlay.class.getName());;

    private static int turn = 0;

    public GamePlay() {
        Config config = InterfaceConfig.getInstance();
        MAP_HEIGHT  = config.getIntValue("map.height");
        MAP_WIDTH   = config.getIntValue("map.width");
        LANE_WIDTH  = config.getIntValue("map.lane.width");

        config = Config.getInstance();
        REFRESH_RATE         = config.getIntValue("game.refreshRate");
        CREEP_GENERATE_TIME  = config.getIntValue("game.creep.generateTime");
        RANGED_CREEP_NUMBERS = config.getIntValue("game.creep.ranged.numbers");
        MELEE_CREEP_NUMBERS  = config.getIntValue("game.creep.melee.numbers");

        GreenUnits = new unitList("GREEN");
        RedUnits   = new unitList("RED");
    }

    public void play() {

        new Thread(() -> {
            logger.info("the thread that checks and add turn is running");
            try {
                Thread.sleep(REFRESH_RATE);
                turn ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        AtomicInteger lastTurnCreepSpawn = new AtomicInteger(turn);
        new Thread(()->{
            logger.info("the thread that for spawn creep is running");
            if (turn - lastTurnCreepSpawn.get() == 60) {
                spawnCreep();
                lastTurnCreepSpawn.set(turn);
            }
        }).start();

        ArrayList<Tower> towers = new ArrayList<>(GreenUnits.getTowers());
        towers.addAll(RedUnits.getTowers());
        TowerRunnable towerRunnable = new TowerRunnable(towers);
        new Thread(towerRunnable).start();
        int lastTurn = turn;
        logger.info("game start");
        while(true) {
            if (turn - lastTurn == 1) {
                checkMap();
                hpRegenerateAll();
                //TODO: communicate
                communicate();
                lastTurn = turn;
                

            }
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

        ArrayList<Hero> greenHeroes = GreenUnits.getHeros();
        ArrayList<Hero> redHeroes = RedUnits.getHeros();

        ArrayList<Barrack> greenBarracks = GreenUnits.getBarracks();
        ArrayList<Barrack> redBarracks = RedUnits.getBarracks();
        
        //attacker: green
        main: for (Unit green: greenUnits){
            if(green.canHit(RedUnits.getAncient())){
                green.setDefender(RedUnits.getAncient());
                green.setStatusAttacker(true);
                continue main;
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

    void communicate() {

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
}
