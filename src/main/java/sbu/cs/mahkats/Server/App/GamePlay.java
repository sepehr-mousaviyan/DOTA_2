package sbu.cs.mahkats.Server.App;


import com.sun.javafx.print.Units;
import org.javatuples.Tuple;
import sbu.cs.mahkats.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Unit.Movable.Creep.Melee;
import sbu.cs.mahkats.Unit.Movable.Creep.Ranged;
import sbu.cs.mahkats.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Unit.Unit;
import sbu.cs.mahkats.Unit.unitList;
import sbu.cs.mahkats.Unit.Building.Barrack.Barrack;

import java.util.ArrayList;

public class GamePlay {


    private final int REFRESH_RATE = 1000;
    private final int CREEP_GENERATE_TIME = 60;
    
    private final int RANGED_CREEP_NUMBERS = 1;
    private final int MELEE_CREEP_NUMBERS = 3;
    
    private unitList GreenUnits;
    private unitList RedUnits;

    private static int turn = 0;

    public GamePlay() {
        GreenUnits = new unitList("GREEN");
        RedUnits = new unitList("RED");
    }

    public static int getTurn() {
        return turn;
    }

    public void play() {

        new Thread(() -> {
            try {
                Thread.currentThread().sleep(REFRESH_RATE);
                turn ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        
        int lastTurnCreepSpawn = turn;
        new Thread(()->{
            if (turn - lastTurnCreepSpawn == 60) {
                spawnCreep();
                lastTurnCreepSpawn = turn;
            }
        }).start();
        while(true) {
            if (turn - lastTurn == 1) {
                checkMap();
                lastTurn = turn;
                
                
            }
        }

    }
    
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

    public ArrayList checkMap() {
        ArrayList<Unit> greenUnits = GreenUnits.getAll();
        ArrayList<Unit> redUnits = RedUnits.getAll();

        ArrayList<Creep> greenCreeps = GreenUnits.getCreeps();
        ArrayList<Creep> redCreeps = RedUnits.getCreeps();

        ArrayList<Tower> greenTowers = GreenUnits.getTowers();
        ArrayList<Tower> redTowers = RedUnits.getTowers();

        ArrayList<Hero> greenHeros = GreenUnits.getHeros();
        ArrayList<Hero> redHeros = RedUnits.getHeros();

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
            for(Hero redH : redHeros) {
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
            for(Hero greenH : greenHeros) {
                if (red.canHit((Unit) greenH)) {
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
        return null;
    }

    public void spawnCreep(){
        
        String greenLane  = whichLane((int) (Math.random() * GreenUnits.getTowers().size()) + 1);
        String redLane  = whichLane((int) (Math.random() * RedUnits.getTowers().size()) + 1);
        ArrayList<Creep> redCreep = new ArrayList<>(); 
        ArrayList<Creep> greenCreep = new ArrayList<>(); 
        

        for (int i = 0; i < RANGED_CREEP_NUMBERS; i++) {
            greenCreep.add(new Ranged(GreenUnits.getBarrack(greenLane).getLocation_x(),
                                        GreenUnits.getBarrack(greenLane).getLocation_y(),
                                        greenLane, "GREEN"));
            GreenUnits.add(greenCreep);

            redCreep.add(new Ranged(RedUnits.getBarrack(redLane).getLocation_x(),
                                        RedUnits.getBarrack(redLane).getLocation_y(),
                                        greenLane, "RED"));
            RedUnits.add(redCreep);
            }
        for (int i = 0; i < MELEE_CREEP_NUMBERS; i++) {
            greenCreep.add(new Melee(GreenUnits.getBarrack(greenLane).getLocation_x(),
                                        GreenUnits.getBarrack(greenLane).getLocation_y(),
                                        greenLane, "GREEN"));
            GreenUnits.add(greenCreep);

            redCreep.add(new Melee(RedUnits.getBarrack(redLane).getLocation_x(),
                                        RedUnits.getBarrack(redLane).getLocation_y(),
                                        greenLane, "RED"));
            RedUnits.add(redCreep);
        }
        CreepRunnable greenCreepRunnable = new CreepRunnable(greenCreep);
        new Thread(creepRunnable).start(); 
        CreepRunnable redCreepRunnable = new CreepRunnable(redCreep);
        new Thread(creepRunnable).start(); 
    }
}
