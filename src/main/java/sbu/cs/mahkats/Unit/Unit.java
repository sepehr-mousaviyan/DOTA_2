package sbu.cs.mahkats.Unit;

import java.io.Serializable;

public abstract class Unit {
    protected double hp = 0;
    protected double hp_regeneration = 0;
    protected double damage = 0;
    protected double armor = 0;
    protected double range = 0;
    protected String teamName = "";
    public Unit(String teamName) {
        this.teamName = teamName;
    }

    protected int Location_x = 0;
    protected int Location_y = 0;


    public void Hp_regenerate(){
        hp  = hp  + hp_regeneration;
    }
    //TODO damage giving for hero

    public void takeDamage(double damage) {
        hp = hp - (damage - armor);
    }

}
