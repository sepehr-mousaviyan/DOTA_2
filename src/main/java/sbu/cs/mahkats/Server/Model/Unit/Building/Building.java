package sbu.cs.mahkats.Server.Model.Unit.Building;

import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Server.Model.Unit.Unit;

public  abstract class Building implements Unit {
    public String toString() {
        return new Api().toJson(new Pair<>("hp", hp),
                new Pair<>("hp_regeneration", hp_regeneration),
                new Pair<>("damage", damage),
                new Pair<>("armor", armor),
                new Pair<>("range", range),
                new Pair<>("Location_x", Location_x),
                new Pair<>("Location_y", Location_y)).toString();
    }
}
