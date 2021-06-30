package sbu.cs.mahkats.Server.Unit.Movable;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.Unit.Unit;

public abstract class Movable extends Unit {
    protected int level = 1;
    protected double mana = 0;
    protected double max_mana = 0;
    protected double mana_regeneration = 0;

    protected int bottom_max_y;
    protected int bottom_min_x;
    protected int bottom_min_y;
    protected int bottom_max_x;
    protected int top_max_x;
    protected int top_min_y;
    protected int top_min_x;
    protected int top_max_y;
    protected int middle_max_x;
    protected int middle_min_y;
    protected int middle_min_x;
    protected int middle_max_y;
    protected int width_lane;
    protected int chunk_size;

    public Movable(String teamName, String unitType , int code) {
        super(teamName, unitType, code);
        Config config = InterfaceConfig.getInstance();
        bottom_max_y = config.getIntValue("bottom.lane.max.y");
        bottom_min_x = config.getIntValue("bottom.lane.min.x");
        bottom_min_y = config.getIntValue("bottom.lane.min.y");
        bottom_max_x = config.getIntValue("bottom.lane.max.x");
        top_max_x = config.getIntValue("middle.lane.max.x");
        top_min_y = config.getIntValue("middle.lane.min.y");
        top_min_x = config.getIntValue("middle.lane.min.x");
        top_max_y = config.getIntValue("middle.lane.max.y");
        middle_max_x = config.getIntValue("top.lane.max.x");
        middle_min_y = config.getIntValue("top.lane.min.y");
        middle_min_x = config.getIntValue("top.lane.min.x");
        middle_max_y = config.getIntValue("top.lane.max.y");
        width_lane = config.getIntValue("map.lane.width");
    }

    /**
     * go to psotion x y
     *
     * @param Location_x
     * @param Location_y
     */
    public void move(int Location_x, int Location_y) {
        if(checkValidLocation()) {
            this.Location_x = Location_x;
            this.Location_y = Location_y;
        }

    }

    protected boolean checkValidLocation(){
        boolean isValid = false;
        if(Location_x < 255 && Location_y < 255){
            isValid = true;
        }
        if(Location_x > bottom_min_x && Location_x > top_min_x){
            if(Location_x < bottom_min_x+width_lane && Location_x < top_min_x+width_lane){
                isValid = true;
            }
        }
        if(Location_y > bottom_min_y && Location_y > top_min_y){
            if(Location_y < bottom_min_y+width_lane && Location_y < top_max_y+width_lane){
                isValid = true;
            }
        }
        if(Location_x < middle_max_y && Location_x > middle_min_x){
            int m = (middle_max_y-middle_min_y) / (middle_max_x - middle_min_x);
            if(m*Location_x + middle_min_y < Location_y && m*Location_x + middle_max_y > Location_y){
                isValid = true;
            }
        }
        return isValid;
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

    public double getMana() {
        return mana;
    }
    public double getMana_regeneration() {
        return mana_regeneration;
    }
    public int getLevel() {
        return level;
    }
}