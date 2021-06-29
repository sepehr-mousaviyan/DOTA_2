package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class MovableUnitData extends GamePlayData{
    protected int level;
    protected int mana;
    protected int mana_regeneration;
    protected String ability1;
    protected String ability2;
    protected String ability3;

    public MovableUnitData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int defender, boolean isDie, int code, int level, int mana, int mana_regeneration, String ability1, String ability2, String ability3, String teamName) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code ,teamName);
        this.level = level;
        this.mana = mana;
        this.mana_regeneration = mana_regeneration;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.ability3 = ability3;
    }

    public MovableUnitData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int defender, boolean isDie, int code, int level, int mana, int mana_regeneration, String teamName) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code, teamName);
        this.level = level;
        this.mana = mana;
        this.mana_regeneration = mana_regeneration;
    }

    public MovableUnitData(Long token, String error) {
        super(token, error);
    }

    public int getLevel() {
        return level;
    }

    public int getMana() {
        return mana;
    }

    public int getMana_regeneration() {
        return mana_regeneration;
    }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public String getAbility3() {
        return ability3;
    }

    @Override
    public JsonObject makeJson() {
        String str = super.makeJson().toString();
        if(error != null) {
            str = str.substring(1, str.length() - 1) + ", ";
            return new Api().toJson(str + new Api().toJson(new Pair<>("level", level),
                    new Pair<>("mana", mana),
                    new Pair<>("mana_regeneration", mana_regeneration),
                    new Pair<>("ability1", ability1),
                    new Pair<>("ability2", ability2),
                    new Pair<>("ability3", ability3)).toString());
        }
        return new Api().toJson(str);
    }
}
