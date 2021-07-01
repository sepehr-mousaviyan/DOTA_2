package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class CreepData extends MovableUnitData{
    private String typeCreep;

    public CreepData(long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, boolean isAttacking, int defender, boolean isDie, int code, int level, double mana, double mana_regeneration, String typeCreep, String teamname, int Location_x, int Location_y) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code, level, mana, mana_regeneration, teamname, Location_x, Location_y);
        this.typeCreep = typeCreep;
    }

    public CreepData(Long token, String error) {
        super(token, error);
    }

    public String getTypeCreep() {
        return typeCreep;
    }

    @Override
    public JsonObject makeJson() {
        String str = super.makeJson().toString();
        str = str.substring(0 , str.length() - 1) + ", ";
        String added = Api.toJson(new Pair<>("typeCreep", typeCreep)).toString();
        added = added.substring(1);
        return Api.toJson(str + added);
    }
}
