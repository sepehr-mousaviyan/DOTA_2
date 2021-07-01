package sbu.cs.mahkats.Server.Unit.Movable.Creep;

import sbu.cs.mahkats.Server.Unit.Movable.Movable;

public class Creep extends Movable {
    String typeCreep;

    String lane;

    public Creep(String lane, String teamName, String typeCreep, int code) {
        super(teamName, "Creep", code);
        this.lane = lane;
        this.typeCreep = typeCreep;
    }

    public String getType() {
        return typeCreep;
    }

    public String getLane() {
        return lane;
    }
}

