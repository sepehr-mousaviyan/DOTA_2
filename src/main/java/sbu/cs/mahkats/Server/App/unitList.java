package sbu.cs.mahkats.Server.App;

import sbu.cs.mahkats.Unit.Building.Ancient.Ancient;
import sbu.cs.mahkats.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Unit.Building.Building;
import sbu.cs.mahkats.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Unit.Movable.Movable;
import sbu.cs.mahkats.Unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class unitList {
    String teamName;
    List <Creep> creeps = new ArrayList<>();
    List <Hero> heros = new ArrayList<>();
    List <Barrack> barracks = new ArrayList<>();
    List <Tower> towers = new ArrayList<>();
    Ancient ancient;
    public unitList(String teamName) {
        this.teamName = teamName;
        ancient = new Ancient(teamName);
        this.add(new Tower("Bottom", teamName));
        this.add(new Tower("Middle", teamName));
        this.add(new Tower("Top", teamName));
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
}

