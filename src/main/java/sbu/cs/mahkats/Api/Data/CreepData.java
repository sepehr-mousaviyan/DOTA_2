package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class CreepData extends MovableUnitData{
    private String typeCreep;

    public CreepData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, boolean isAttacking, int defender, boolean isDie, int code, int level, double mana, double mana_regeneration, String ability1, String ability2, String ability3, String typeCreep, String teamName, int Location_x, int Location_y) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code, level, mana, mana_regeneration, ability1, ability2, ability3, teamName, Location_x, Location_y);
    }

    public CreepData(Long token, String error) {
        super(token, error);
    }

    public CreepData(long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, boolean isAttacking, int defender, boolean isDie, int code, int level, double mana, double mana_regeneration, String typeCreep, String teamname, int Location_x, int Location_y) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code, level, mana, mana_regeneration, teamname, Location_x, Location_y);
        this.typeCreep = typeCreep;
    }

    public String getTypeCreep() {
        return typeCreep;
    }

    @Override
    public JsonObject makeJson() {
        String str = super.makeJson().toString();
        str = str.substring(0 , str.length() - 1) + ", ";
        String added = new Api().toJson(new Pair<>("typeCreep", typeCreep)).toString();
        added = added.substring(1);
        return new Api().toJson(str + added);
    }
}
