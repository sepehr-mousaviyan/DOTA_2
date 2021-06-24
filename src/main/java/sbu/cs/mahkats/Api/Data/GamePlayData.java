package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class GamePlayData implements Data{
    protected final Long token;
    protected double hp = 0;
    protected double hp_regeneration = 0;
    protected double minimum_damage = 0;
    protected double maximum_damage = 0;
    protected double armor = 0;
    protected double range = 0;
    protected double experience;
    protected Boolean isAttacking = false;
    protected int code_defender;
    protected boolean isDie = false;
    protected int code;
    protected String error;
    protected int Location_x;
    protected int Location_y;

    public GamePlayData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int code_defender, boolean isDie, int code) {
        this.token = token;
        this.hp = hp;
        this.hp_regeneration = hp_regeneration;
        this.minimum_damage = minimum_damage;
        this.maximum_damage = maximum_damage;
        this.armor = armor;
        this.range = range;
        this.experience = experience;
        this.isAttacking = isAttacking;
        this.code_defender = code_defender;
        this.isDie = isDie;
        this.code = code;
    }

    public GamePlayData(Long token, String error) {
        this.token = token;
        this.error = error;
    }

    public Long getToken() {
        return token;
    }

    public double getHp() {
        return hp;
    }

    public double getHp_regeneration() {
        return hp_regeneration;
    }

    public double getMinimum_damage() {
        return minimum_damage;
    }

    public double getMaximum_damage() {
        return maximum_damage;
    }

    public double getArmor() {
        return armor;
    }

    public double getRange() {
        return range;
    }

    public double getExperience() {
        return experience;
    }

    public Boolean getAttacking() {
        return isAttacking;
    }

    public int getCode_defender() {
        return code_defender;
    }

    public boolean isDie() {
        return isDie;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public JsonObject makeJson(){
        return new Api().toJson(new Pair<>("hp", hp),
                new Pair<>("hp_regeneration", hp_regeneration),
                new Pair<>("damage", minimum_damage),
                new Pair<>("armor", armor),
                new Pair<>("range", range),
                new Pair<>("Location_x", Location_x),
                new Pair<>("Location_y", Location_y));
    }
}
