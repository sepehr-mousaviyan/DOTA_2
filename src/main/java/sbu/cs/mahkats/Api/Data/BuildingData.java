package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;

public class BuildingData extends GamePlayData{
    private String typeBuilding;

    public String getTypeBuilding() {
        return typeBuilding;
    }

    public BuildingData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int code_defender, boolean isDie, int code , String typeBuilding, String teamName) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, code_defender, isDie, code, teamName);
        this.typeBuilding = typeBuilding;
    }

    public BuildingData(Long token, String error) {
        super(token, error);
    }


    @Override
    public JsonObject makeJson() {
        return super.makeJson();
    }
}
