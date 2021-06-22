package sbu.cs.mahkats.Server.Model.Unit.Movable;

import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Server.Model.Unit.Unit;

public abstract class Movable implements Unit {
    //TODO move


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
