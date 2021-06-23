package sbu.cs.mahkats.Unit;

import sbu.cs.mahkats.Unit.Building.Ancient.Ancient;
import sbu.cs.mahkats.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Unit.Building.Building;
import sbu.cs.mahkats.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Unit.Movable.Movable;
import sbu.cs.mahkats.Unit.Unit;

import java.util.ArrayList;

public class unitList {
    private String teamName;
    private ArrayList <Creep> creeps = new ArrayList<>();
    private ArrayList <Hero> heros = new ArrayList<>();
    private ArrayList <Barrack> barracks = new ArrayList<>();
    private ArrayList <Tower> towers = new ArrayList<>();
    private Ancient ancient;
    private final int BASE_TOWER_NUMBERS = 3;
    private final int LANE_TOWER_NUMBERS = 3;
    public unitList(String teamName) {
        this.teamName = teamName;
        //ancient unit making
        ancient = new Ancient(teamName);

        //towers unit making
        for (int i = 0; i < BASE_TOWER_NUMBERS) {
            this.towers.add(new Tower("base", teamName, i));
        }
    
        for (int i = 0; i < LANE_TOWER_NUMBERS) {
            this.towers.add(new Tower("bottom", teamName, i));
        }
        
        for (int i = 0; i < LANE_TOWER_NUMBERS) {
            this.towers.add(new Tower("middle", teamName, i));
        }

        for (int i = 0; i < LANE_TOWER_NUMBERS) {
            this.towers.add(new Tower("top", teamName, i));
        }

        //barracks unit making
        
    }
    public void add(Creep creep) {
        creeps.add(creep);
    }
    public void add(Hero hero) {
        heros.add(hero);
    }
    public void add(Barrack barrack) {
        barracks.add(barrack);
    }
    public void add(Tower tower) {
        towers.add(tower);
    }

    public void add(ArrayList<Creep> creeps) {
        for(Creep c : creeps){
            add(c);
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


    public Barrack getBarrack(String lane) {
        for(Barrack b : barracks){
            if(b.getLane().equals(lane)){
                return b;
            }
        }
        return null;
    }
    
    public ArrayList<Barrack> getBarracks(){
        return barracks;
    }

    public Ancient getAncient(){
        return ancient;
    }

    public ArrayList<Unit> getAll(){
        ArrayList<Unit> all = new ArrayList<Unit>();
        all.addAll(creeps);
        all.addAll(towers);
        all.addAll(barracks);
        all.addAll(heros);
        all.add(ancient);
        return all;
    }

}

