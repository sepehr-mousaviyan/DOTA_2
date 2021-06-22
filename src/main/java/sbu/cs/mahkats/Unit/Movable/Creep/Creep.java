package sbu.cs.mahkats.Unit.Movable.Creep;

import sbu.cs.mahkats.Unit.Movable.Movable;

public class Creep extends Movable {

    String lane;
    public Creep(String lane, String teamName) {
        super(teamName);
        this.lane = lane;
    }

    public String getLane() {
        return lane;
    }
}

