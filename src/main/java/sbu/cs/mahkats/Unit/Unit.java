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
    protected Unit defender = null;
    
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

    public double getRange() {
        return range;
    }

    public double getDamage() {
        return damage;
    }

    public void setDefender(Unit defender){
        this.defender = defender;
    }

    public Unit getDefender(){
        return defender;
    }

    public void setStatusAttacker(boolean isTrue){
        this.isAttacking = isTrue;
    }

    public boolean getStatusAttacker(){
        return isTrue;
    }


    public boolean canHit(Unit defender) {
        if(range ==1) {
            for(int i = Location_x - 1 ; i <= Location_x + 1 ; i++){
                for(int j = Location_y - 1 ; j <= Location_y + 1 ; j++){
                    if(defender.getLocation_x() == i && defender.getLocation_y() == j){
                        isAttacking = true;
                        return isAttacking;
                    }
                }
            }
        }
        if(range == 2 || range == 3){
            if (Math.abs(defender.getLocation_x() - Location_x) + Math.abs(defender.getLocation_y() - Location_y) <= range) {
                isAttacking = true;
                return true;
            }
        }
        isAttacking = false;
        return isAttacking;
    }
}
