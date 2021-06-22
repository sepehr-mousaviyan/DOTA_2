package sbu.cs.mahkats.Unit.Movable;

import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Unit.Movable.Hero.Ability;
import sbu.cs.mahkats.Unit.Unit;

public abstract class Movable extends Unit {
    int level;
    protected int experience;
    int mana;
    int mana_regeneration;
    Ability ability1;
    Ability ability2;
    Ability ability3;
    Ability ability4;

    public Movable(String teamName) {
        super(teamName);
    }

    //TODO move
    public void move(int Location_x, int Location_y, String lane) {
        this.Location_x = Location_x;
        this.Location_y = Location_y;


        switch (lane) {
            case "TOP":

        }
    }

    @Override
    public String toString() {
        return new Api().toJson(new Pair<>("hp", hp),
                new Pair<>("hp_regeneration", hp_regeneration),
                new Pair<>("damage", damage),
                new Pair<>("armor", armor),
                new Pair<>("range", range),
                new Pair<>("Location_x", Location_x),
                new Pair<>("Location_y", Location_y),
                new Pair<>("level", level),
                new Pair<>("experience", experience),
                new Pair<>("mana", mana),
                new Pair<>("mana_regeneration", mana_regeneration),
                new Pair<>("ability1", ability1),
                new Pair<>("ability2", ability2),
                new Pair<>("ability3", ability3),
                new Pair<>("ability4", ability4)).toString();
    }
}
