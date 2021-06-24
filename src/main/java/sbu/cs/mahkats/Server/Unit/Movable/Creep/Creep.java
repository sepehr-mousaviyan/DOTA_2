package sbu.cs.mahkats.Unit.Movable.Creep;

import sbu.cs.mahkats.Unit.Movable.Movable;

public class Creep extends Movable {
    String typeCreep;
    String lane;

    public Creep(String lane, String teamName , String typeCreep) {
        super(teamName, "Creep");
        this.lane = lane;
        this.typeCreep = typeCreep;
    }

    public String getType(){ return typeCreep;}

    public String getLane() {
        return lane;
    }
}
