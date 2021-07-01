package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class HeroData extends MovableUnitData{

    private String heroType = "";

    public String getHeroType() {
        return heroType;
    }

    public HeroData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int defender, boolean isDie, int code, int level, double mana, double mana_regeneration, String ability1, String ability2, String ability3, String teamName, int Location_x, int Location_y, String heroType) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code, level, mana, mana_regeneration, ability1, ability2, ability3, teamName, Location_x, Location_y);
        this.heroType = heroType;
    }

    public HeroData(Long token, String error) {
        super(token, error);
    }


    @Override
    public JsonObject makeJson() {
        String str = super.makeJson().toString();
        if(error != null) {
            str = str.substring(0, str.length() - 1) + ", ";
            String added = Api.toJson(new Pair<>("heroType", heroType)).toString();
            added = added.substring(1);
            return Api.toJson(str + added);
        }
        return Api.toJson(str);
    }
}
