package sbu.cs.mahkats.Unit;

import java.io.Serializable;

public abstract class Unit {
    protected double hp = 0;
    protected double hp_regeneration = 0;
    protected double damage = 0;
    protected double armor = 0;
    protected double range = 0;
    protected String teamName = "";
    protected Boolean isAttacking = false;
    protected Unit defender = NULL;
    
    protected int Location_x = 0;
    protected int Location_y = 0;

    public Unit(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName(){
        return teamName;
    }

    public void Hp_regenerate(){
        hp  = hp  + hp_regeneration;
    }
    //TODO damage giving for hero

    public void takeDamage(double damage) {
        hp = hp - (damage - armor);
    }

    public int getLocation_x() {
        return Location_x;
    }

    public int getLocation_y() {        
        return Location_y;
    }

    public int getRange() {
        return range;
    }

    public void setDefender(Unit defender){
        this.defender = defender;
    }

    public void setStatusAttacker(boolean isTrue){
        this.isAttacker = isTrue;
    }

    public boolean canHit(Unit defender) {
        return false;
        //if(Location_x - range > defender.getLocation_x() && Location_x + range < defender.getLocation_x()
        //&& Location_x - range > defender.getLocation_x() && Location_x + range < defender.getLocation_x())
    }
}
