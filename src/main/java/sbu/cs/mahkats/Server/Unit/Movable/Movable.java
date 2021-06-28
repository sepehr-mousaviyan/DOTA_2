package sbu.cs.mahkats.Server.Unit.Movable;

import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Unit;

public abstract class Movable extends Unit {
    protected int level;
    protected int mana;
    protected int max_mana;
    protected int mana_regeneration;

    public Movable(String teamName, String unitType , int code) {
        super(teamName, unitType, code);
    }

    /**
     * go to psotion x y
     *
     * @param Location_x
     * @param Location_y
     */
    public void move(int Location_x, int Location_y) {
        this.Location_x = Location_x ;
        this.Location_y = Location_y;

    }

    /**
     * go in direct of a lane to the opponet player ancient
     *
     * @param lane
     */
    public void move(String lane) {
        int temp_chunk_size = chunk_size;
        if(teamName.equals("Red")){
            temp_chunk_size *= -1;
        }
        switch (lane) {
            case "BOTTOM":
                if (Location_x < GamePlay.getMAP_WIDTH()) {
                    Location_x += temp_chunk_size;
                } else if (Location_x == GamePlay.getMAP_WIDTH() && Location_y < GamePlay.getMAP_HEIGHT()) {
                    Location_y += temp_chunk_size;
                }
                break;
            case "MIDDLE":
                if (Location_x < GamePlay.getMAP_WIDTH() && Location_y < GamePlay.getMAP_HEIGHT()) {
                    Location_x += temp_chunk_size;
                    Location_y += temp_chunk_size;
                }
                break;
            case "TOP":
                if (Location_y < GamePlay.getMAP_HEIGHT()) {
                    Location_y += temp_chunk_size;
                } else if (Location_y == GamePlay.getMAP_HEIGHT() && Location_x < GamePlay.getMAP_WIDTH()) {
                    Location_x += temp_chunk_size;
                }
                break;
        }
    }

    public boolean reduceMana(double reduce){
        if(mana < reduce){
            return false;
        }
        mana -= reduce;
        return true;
    }

    public void mana_regeneration(){
        mana += mana_regeneration;
        if(max_mana < mana){
            mana = max_mana;
        }
    }

    public int getMana() {
        return mana;
    }
    public int getMana_regeneration() {
        return mana_regeneration;
    }
    public int getLevel() {
        return level;
    }
}