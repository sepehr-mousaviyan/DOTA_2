package sbu.cs.mahkats.Server.Unit.Building;

import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Server.Unit.Unit;

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

}
