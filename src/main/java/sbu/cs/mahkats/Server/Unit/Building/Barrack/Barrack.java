package sbu.cs.mahkats.Server.Unit.Building.Barrack;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Configuration.Units.BuildingConfig;
import sbu.cs.mahkats.Server.Unit.Building.Building;

public class Barrack extends Building {
    public Barrack(String lane, String teamName , int code) {
        super(teamName, "Barrack" , code);
        this.lane = lane;
    }
}
