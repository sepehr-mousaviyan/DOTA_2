package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;

public class HeroData extends MovableUnitData{
    public HeroData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int defender, boolean isDie, int code, String teamName, int level, int mana, int mana_regeneration, String ability1, String ability2, String ability3, String ability4) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code, teamName, level, mana, mana_regeneration, ability1, ability2, ability3, ability4);
    }

    public HeroData(Long token, String error) {
        super(token, error);
    }

    @Override
    public JsonObject makeJson() {
        return super.makeJson();
    }
}
