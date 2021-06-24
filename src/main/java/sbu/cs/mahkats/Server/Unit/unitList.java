package sbu.cs.mahkats.Unit;

import sbu.cs.mahkats.Unit.Building.Ancient.Ancient;
import sbu.cs.mahkats.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Unit.Building.Barrack.RangedBarrack;
import sbu.cs.mahkats.Unit.Building.Barrack.MeleeBarrack;
import sbu.cs.mahkats.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Unit.Movable.Hero.Hero;
import java.util.ArrayList;

public class unitList {
    private String teamName;
    private ArrayList <Creep> creeps = new ArrayList<>();
    private ArrayList <Hero> heros = new ArrayList<>();
    private ArrayList <MeleeBarrack> meleeBarracks = new ArrayList<>();
    private ArrayList <RangedBarrack> rangedBarracks = new ArrayList<>();

    private ArrayList <Tower> towers = new ArrayList<>();
    private Ancient ancient;
    private final int BASE_TOWER_NUMBERS = 3;
    private final int LANE_TOWER_NUMBERS = 3;
    public unitList(String teamName) {
        this.teamName = teamName;
        //ancient unit making
        ancient = new Ancient(teamName);

        //towers unit making
        for (int i = 0; i < BASE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower("BASE", teamName, i));
        }
    
        for (int i = 0; i < LANE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower("BOTTOM", teamName, i));
        }
        
        for (int i = 0; i < LANE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower("MIDDLE", teamName, i));
        }

        for (int i = 0; i < LANE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower("TOP", teamName, i));
        }

        //barracks unit making
        this.meleeBarracks.add(new MeleeBarrack("TOP", teamName));
        this.meleeBarracks.add(new MeleeBarrack("MIDDLE", teamName));
        this.meleeBarracks.add(new MeleeBarrack("BOTTOM", teamName));
        
        this.rangedBarracks.add(new RangedBarrack("TOP", teamName));
        this.rangedBarracks.add(new RangedBarrack("MIDDLE", teamName));
        this.rangedBarracks.add(new RangedBarrack("BOTTOM", teamName));    
    }
    
    public void add(Creep creep) {
        creeps.add(creep);
    }
    public void add(Hero hero) {
        heros.add(hero);
    }
    public void add(MeleeBarrack meleeBarrack) {
        meleeBarracks.add(meleeBarrack);
    }
    public void add(Tower tower) {
        towers.add(tower);
    }

    public void addCreeps(ArrayList<Creep> creeps) {
        for(Creep c : creeps){
            creeps.add(c);
        }
    }

    public void addMeleeBarracks(ArrayList<MeleeBarrack> barracks) {
        for(MeleeBarrack b : barracks){
            meleeBarracks.add(b);
        }
    }

    public void addRangedBarracks(ArrayList<RangedBarrack> barracks) {
        for(RangedBarrack b : barracks){
            rangedBarracks.add(b);
        }
    }
    
    public Tower getTower(String lane) {
        for(Tower t : towers){
            if(t.getLane().equals(lane)){
                return t;
            }
        }
        return null;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<Hero> getHeros() {
        return heros;
    }

    public Creep getCreep(String lane) {
        for(Creep c : creeps){
            if(c.getLane().equals(lane)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Creep> getCreeps() {
        return creeps;
    }


    public RangedBarrack getRangedBarrack(String lane) {
        for(RangedBarrack rangedB : rangedBarracks){
            if(rangedB.getLane().equals(lane)){
                return rangedB;
            }
        }
        return null;
    }

    public MeleeBarrack getMeleeBarracks(String lane) {
        for(MeleeBarrack meleeB : meleeBarracks){
            if(meleeB.getLane().equals(lane)){
                return meleeB;
            }
        }
        return null;
    }

    public ArrayList<Barrack> getBarracks() {
        ArrayList<Barrack> barracks = new ArrayList<>();
        barracks.addAll(meleeBarracks);
        barracks.addAll(rangedBarracks);
        return barracks;
    }

    public Ancient getAncient(){
        return ancient;
    }

    public ArrayList<Unit> getAll(){
        ArrayList<Unit> all = new ArrayList<Unit>();
        all.addAll(creeps);
        all.addAll(towers);
        all.addAll(meleeBarracks);
        all.addAll(rangedBarracks);
        all.addAll(heros);
        all.add(ancient);
        return all;
    }

    public void remove(Unit unit){
        for(Creep creep : creeps){
            if(creep.equals(unit)){
                creeps.remove(creep);
                if(creep.getType().equals("Ranged")) {
                    for (RangedBarrack rangedBarrack : rangedBarracks) {
                        rangedBarrack.removeCreep(creep);
                    }
                }
                else{
                    for (MeleeBarrack meleeBarrack : meleeBarracks) {
                        meleeBarrack.removeCreep(creep);
                    }
                }
                return;
            }
        }

        rangedBarracks.removeIf(rangedBarrack -> rangedBarrack.equals(unit));
        meleeBarracks.removeIf(meleeBarrack -> meleeBarrack.equals(unit));
        towers.removeIf(tower -> tower.equals(unit));
    }
}
