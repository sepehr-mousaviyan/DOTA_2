package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

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
        String str = super.makeJson().toString();
        if(error != null) {
            str = str.substring(0, str.length() - 1) + ", ";
            String added = Api.toJson(new Pair<>("typeBuilding", typeBuilding)).toString();
            added = added.substring(1);
            return Api.toJson(str + added);
        }
        return Api.toJson(str);
    }
}
