package sbu.cs.mahkats.Unit.Building.Barrack;

import sbu.cs.mahkats.Unit.Building.Building;

public class Barrack extends Building {
    public Barrack(String lane, String teamName) {
        super(teamName);
        this.lane = lane;
    }
}