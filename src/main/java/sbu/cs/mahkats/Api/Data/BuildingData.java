package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;

public class BuildingData extends GamePlayData {
    private String typeBuilding;

    public BuildingData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int code_defender, boolean isDie, int code, String typeBuilding, String teamName, int Location_x, int Location_y) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, code_defender, isDie, code, teamName, Location_x, Location_y);
        this.typeBuilding = typeBuilding;
    }

    public BuildingData(Long token, String error) {
        super(token, error);
    }

    public String getTypeBuilding() {
        return typeBuilding;
    }

    @Override
    public JsonObject makeJson() {
        return super.makeJson();
    }

    @Override
    public String toString() {
        return "BuildingData{" +
                "typeBuilding='" + typeBuilding + '\'' +
                ", token=" + token +
                ", hp=" + hp +
                ", isAttacking=" + isAttacking +
                ", isDie=" + isDie +
                ", code=" + code +
                ", error='" + error + '\'' +
                ", Location_x=" + Location_x +
                ", Location_y=" + Location_y +
                ", max_Location_y=" + max_Location_y +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
