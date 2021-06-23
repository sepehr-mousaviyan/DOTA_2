package sbu.cs.mahkats.Unit.Movable;

import javax.lang.model.util.ElementScanner6;

import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Unit.Movable.Hero.Ability;
import sbu.cs.mahkats.Unit.Unit;

public abstract class Movable extends Unit {
    int level;

    int mana;
    int mana_regeneration;
    Ability ability1;
    Ability ability2;
    Ability ability3;
    Ability ability4;

    public Movable(String teamName) {
        super(teamName);
    }

    /**
     * go to psotion x y 
     * @param Location_x
     * @param Location_y
     */
    public void move(int Location_x, int Location_y) {
        this.Location_x = Location_x;
        this.Location_y = Location_y;
        
    }

    /**
     * go in direct of a lane to the opponet player ancient
     * @param lane
     */
    public void move(String lane){
        switch (lane) {
            case "BOTTOM" :
                if (Location_x < GamePlay.getMAP_WIDTH()){
                    Location_x++;
                }
                else if (Location_x == GamePlay.getMAP_WIDTH() && Location_y < GamePlay.getMAP_HEIGHT()){
                    Location_y++;
                }
                break;
            case "MIDDLE" :
                if (Location_x < GamePlay.getMAP_WIDTH() && Location_y < GamePlay.getMAP_HEIGHT()){
                    Location_x++;
                    Location_y++;
                }
                break;
            case "TOP" :
                if (Location_y < GamePlay.getMAP_HEIGHT()){
                    Location_y++;
                }
                else if (Location_y == GamePlay.getMAP_HEIGHT() && Location_x < GamePlay.getMAP_WIDTH()){
                    Location_x++;
                }
                break;
        }
    }

    
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
