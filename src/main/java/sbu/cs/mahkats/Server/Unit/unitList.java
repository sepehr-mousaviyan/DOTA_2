package sbu.cs.mahkats.Server.Unit;

import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Building.Ancient.Ancient;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.MeleeBarrack;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.RangedBarrack;
import sbu.cs.mahkats.Server.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;

import java.util.ArrayList;

public class unitList {
    private String teamName;
    private final ArrayList<Creep> creeps = new ArrayList<>();
    private final ArrayList<Hero> heroes = new ArrayList<>();
    private final ArrayList<MeleeBarrack> meleeBarracks = new ArrayList<>();
    private final ArrayList<RangedBarrack> rangedBarracks = new ArrayList<>();
    private final ArrayList<Tower> towers = new ArrayList<>();
    private final Ancient ancient;

    public unitList(String teamName, String heroName) {
        this.teamName = teamName;
        //ancient unit making
        ancient = new Ancient(teamName, GamePlay.get_add_Code());

        //towers unit making
        int BASE_TOWER_NUMBERS = 2;
        for (int i = 0; i < BASE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower(teamName, "BASE", i + 1, GamePlay.get_add_Code()));
        }

        int LANE_TOWER_NUMBERS = 3;
        for (int i = 0; i < LANE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower(teamName, "BOTTOM", i + 1, GamePlay.get_add_Code()));
        }

        for (int i = 0; i < LANE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower(teamName, "MIDDLE", i + 1, GamePlay.get_add_Code()));
        }

        for (int i = 0; i < LANE_TOWER_NUMBERS; i++) {
            this.towers.add(new Tower(teamName, "TOP", i + 1, GamePlay.get_add_Code()));
        }

        //barracks unit making
        this.meleeBarracks.add(new MeleeBarrack("TOP", teamName , GamePlay.get_add_Code()));
        this.meleeBarracks.add(new MeleeBarrack("MIDDLE", teamName , GamePlay.get_add_Code()));
        this.meleeBarracks.add(new MeleeBarrack("BOTTOM", teamName , GamePlay.get_add_Code()));
        
        this.rangedBarracks.add(new RangedBarrack("TOP", teamName , GamePlay.get_add_Code()));
        this.rangedBarracks.add(new RangedBarrack("MIDDLE", teamName , GamePlay.get_add_Code()));
        this.rangedBarracks.add(new RangedBarrack("BOTTOM", teamName , GamePlay.get_add_Code()));

        this.heroes.add(new Hero(teamName , GamePlay.get_add_Code() , heroName));
    }
    
    public void add(Creep creep) {
        creeps.add(creep);
    }
    public void add(MeleeBarrack meleeBarrack) {
        meleeBarracks.add(meleeBarrack);
    }
    public void add(Tower tower) {
        towers.add(tower);
    }

    public void addCreeps(ArrayList<Creep> creeps) {
        this.creeps.addAll(creeps);
    }

    public void addMeleeBarracks(ArrayList<MeleeBarrack> barracks) {
        this.meleeBarracks.addAll(barracks);
    }

    public void addRangedBarracks(ArrayList<RangedBarrack> barracks) {
        this.rangedBarracks.addAll(barracks);
    }
    
    public Tower getTower(String lane) {
        for(Tower t : towers){
            if(t.getLane().equals(lane)){
                return t;
            }
        }
        return null;
    }

    public Creep getCreep(String lane) {
        for(Creep c : creeps){
            if(c.getLane().equals(lane)){
                return c;
            }
        }
        return null;
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

    public ArrayList<Unit> getAll(){
        ArrayList<Unit> all = new ArrayList<Unit>();
        all.addAll(creeps);
        all.addAll(towers);
        all.addAll(meleeBarracks);
        all.addAll(rangedBarracks);
        all.addAll(heroes);
        all.add(ancient);
        return all;
    }

    public Unit getUnit(int code){
        ArrayList<Unit> all = this.getAll();
        for(Unit unit : all){
            if(unit.getCode() == code){
                return unit;
            }
        }
        return null;
    }

    public Hero getHero(int code){
        for(Hero hero : heroes){
            if(hero.getCode() == code){
                return hero;
            }
        }
        return null;
    }

    public ArrayList<Unit> getAll_withoutHero(){
        ArrayList<Unit> all = new ArrayList<>();
        all.addAll(creeps);
        all.addAll(towers);
        all.addAll(meleeBarracks);
        all.addAll(rangedBarracks);
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

    public Ancient getAncient() {
        return ancient;
    }

    public ArrayList<Creep> getCreeps() {
        return creeps;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

}

