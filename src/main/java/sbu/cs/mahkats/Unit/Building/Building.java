package sbu.cs.mahkats.Unit.Building;

import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Unit.Unit;

public abstract class Building extends Unit {
    protected String lane;

    public Building(String teamName, String lane, String unitType) {
        super(teamName, unitType);
        this.lane = lane;
    }

    public Building(String teamName, String unitType) {
        super(teamName, unitType);
    }
    
    public String getLane(){
        return lane;
    }
    

    public String toString() {
        return new Api().toJson(new Pair<>("hp", hp),
                new Pair<>("hp_regeneration", hp_regeneration),
                new Pair<>("damage", minimum_damage),
                new Pair<>("armor", armor),
                new Pair<>("range", range),
                new Pair<>("Location_x", Location_x),
                new Pair<>("Location_y", Location_y)).toString();
    }
}
