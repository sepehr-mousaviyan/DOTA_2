package sbu.cs.mahkats.Server.Unit;

import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;

public abstract class Unit {
    protected double hp = 0;
    protected double max_hp = 0;
    protected double hp_regeneration = 0;
    protected double minimum_damage = 0;
    protected double maximum_damage = 0;
    protected double armor = 0;
    protected double max_armor = 0;
    protected double range = 0;
    protected double experience;
    protected String teamName;
    protected Boolean isAttacking = false;
    protected Unit defender = null;
    protected  boolean isDie = false;
    protected final String unitType;
    protected final int code;
    protected final int chunk_size;
    
    protected int Location_x = 0;
    protected int Location_y = 0;

    public Unit(String teamName, String unitType , int code) {
        this.teamName = teamName;
        this.unitType = unitType;
        this.code = code;
        Config config = InterfaceConfig.getInstance();
        chunk_size = config.getIntValue("game.chunk.size");
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setHp_regeneration(double hp_regeneration) {
        this.hp_regeneration = hp_regeneration;
    }

    public void setMinimum_damage(double minimum_damage) {
        this.minimum_damage = minimum_damage;
    }

    public void setMaximum_damage(double maximum_damage) {
        this.maximum_damage = maximum_damage;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public Boolean getAttacking() {
        return isAttacking;
    }

    public void setAttacking(Boolean attacking) {
        isAttacking = attacking;
    }

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    public int getCode() {
        return code;
    }

    public void setLocation_x(int location_x) {
        Location_x = location_x;
    }

    public void setLocation_y(int location_y) {
        Location_y = location_y;
    }

    public String getTeamName(){
        return teamName;
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

    public double getMinimum_damage() {
        return minimum_damage;
    }

    public double getMaximum_damage() {
        return maximum_damage;
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
        return isAttacking;
    }

    public String getUnitType() {
        return unitType;
    }

    public boolean getStatusDie(){
        return isDie;
    }

    public double getHp_regeneration(){ return hp_regeneration; }

    public void hp_regenerate() {
        hp = hp + hp_regeneration;
        if (hp > 100) {
            hp = 100;
        }
    }

    public void reduce_hp(double reduce){ hp -= reduce; }

    public void reduceDamage(double reduced){
        this.minimum_damage -= reduced;
        this.maximum_damage -= reduced;
    }

    public void addDamage(double added){
        this.minimum_damage += added;
        this.maximum_damage += added;
    }

    public double getDamage(){
        return (Math.random()  * (maximum_damage - minimum_damage)) + minimum_damage;
    }

    /**
     * reduce the hp of this unit
     * @param damage
     */
    public void takeDamage(double damage) {
        if((damage - armor) > hp){
            hp = 0;
            isDie = false;
            this.destroy();
        }
        hp = hp - (damage - armor);
    }

    public void takeDamage(double damage , Hero hero) {
        if((damage - armor) > hp){
            hp = 0;
            isDie = true;
            hero.addRegularExperience(this.experience);
            this.destroy();
        }
        hp = hp - (damage - armor);
    }

    /**
     * check if unit can hit defender return true and set status of attacking unit is on
     * @param defender
     * @return status of attacking
     */
    public boolean canHit(Unit defender) {
        if(range == 1) {
            for(int i = Location_x/chunk_size - chunk_size ; i <= Location_x/chunk_size + chunk_size ; i++){
                for(int j = Location_y - 1 ; j <= Location_y + 1 ; j++){
                    if(defender.getLocation_x() == i / chunk_size && defender.getLocation_y() == j / chunk_size){
                        isAttacking = true;
                        return isAttacking;
                    }
                }
            }
        }
        if(range == 2 || range == 3){
            if (Math.abs(defender.getLocation_x() - Location_x) + Math.abs(defender.getLocation_y() - Location_y) <= (range * chunk_size)) {
                isAttacking = true;
                return isAttacking;
            }
        }
        isAttacking = false;
        return isAttacking;
    }

    /**
     * check if ability can hit defender return true and set status of attacking unit is on
     * @param defender
     * @param ability
     * @return status of attacking
     */
     public boolean canHit(Unit defender , Ability ability) {
        if(ability.getRange() == 1) {
            for(int i = Location_x/chunk_size - chunk_size ; i <= Location_x/chunk_size + chunk_size ; i++){
                for(int j = Location_y - 1 ; j <= Location_y + 1 ; j++){
                    if(defender.getLocation_x() == i / chunk_size && defender.getLocation_y() == j / chunk_size){
                        isAttacking = true;
                        return isAttacking;
                    }
                }
            }
        }
        if(ability.getRange() == 2 || ability.getRange() == 3){
            if (Math.abs(defender.getLocation_x() - Location_x) + Math.abs(defender.getLocation_y() - Location_y) <= (range * chunk_size)) {
                isAttacking = true;
                return isAttacking;
            }
        }
        isAttacking = false;
        return isAttacking;
    }

    public void destroy(){
        GamePlay.destroy(this);
        isDie = true;
    }

    public boolean equals(Unit unit){
        return unit.getUnitType().equals(unitType);
    }
}
