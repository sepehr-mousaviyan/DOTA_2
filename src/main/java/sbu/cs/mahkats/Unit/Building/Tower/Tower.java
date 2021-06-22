package sbu.cs.mahkats.Unit.Building.Tower;

import sbu.cs.mahkats.Unit.Building.Building;

public class Tower extends Building {
    String position;
    public Tower(String position, String teamName) {
        super(teamName);
        this.position = position;
    }
}
