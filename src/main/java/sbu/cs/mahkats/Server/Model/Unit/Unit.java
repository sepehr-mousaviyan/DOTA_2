package sbu.cs.mahkats.Server.Model.Unit;

import org.javatuples.Pair;

public class Unit {
    int hp;
    int hp_regeneration;
    int damage;
    int armor;
    int range;
    int Location_x;
    int Location_y;

    //TODO regenerate hp
    public void Hp_regenerate() {
        hp  = hp  + hp_regeneration;
    }
    //TODO damage giving

    //TODO damage taking
    public void takeDamage(Unit Opponent) {
        int damageDealt = Opponent.getDamage();
        hp  = hp  - (damageDealt - armor);
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp_regeneration() {
        return hp_regeneration;
    }

    public void setHp_regeneration(int hp_regeneration) {
        this.hp_regeneration = hp_regeneration;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getLocation_x() {
        return Location_x;
    }

    public void setLocation_x(int location_x) {
        Location_x = location_x;
    }

    public int getLocation_y() {
        return Location_y;
    }

    public void setLocation_y(int location_y) {
        Location_y = location_y;
    }
}
