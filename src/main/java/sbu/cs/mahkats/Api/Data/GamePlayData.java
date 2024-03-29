package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;

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
    protected int code_defender = 0;
    protected boolean isDie = false;
    protected int code = 0;
    protected String error = "";
    protected int Location_x = 0;
    protected int Location_y = 0;
    protected int max_Location_y = 0;
    protected String teamName = "";

    public GamePlayData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int code_defender, boolean isDie, int code, String teamName, int Location_x, int Location_y) {
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
        this.teamName = teamName;
        this.Location_x = Location_x;
        this.Location_y = Location_y;
        Config config = InterfaceConfig.getInstance();
        max_Location_y = config.getIntValue("map.height");
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

    public int getLocation_x() {
        return Location_x;
    }

    public int getLocation_y() {
        return Location_y;
    }

    public String getTeamName() {
        return teamName;
    }

    public JsonObject makeJson(){
        if(error != null) {
            return Api.toJson(new Pair<>("token", token),
                    new Pair<>("hp", hp),
                    new Pair<>("hp_regeneration", hp_regeneration),
                    new Pair<>("minimum_damage", minimum_damage),
                    new Pair<>("maximum_damage", maximum_damage),
                    new Pair<>("armor", armor),
                    new Pair<>("range", range),
                    new Pair<>("experience", experience),
                    new Pair<>("isAttacking", isAttacking),
                    new Pair<>("code_defender", code_defender),
                    new Pair<>("isDie", isDie),
                    new Pair<>("code", code),
                    new Pair<>("Location_x", Location_x),
                    new Pair<>("Location_y", max_Location_y - Location_y));
        }
        return Api.toJson(new Pair<>("error", error), new Pair<>("token", token));
    }
}