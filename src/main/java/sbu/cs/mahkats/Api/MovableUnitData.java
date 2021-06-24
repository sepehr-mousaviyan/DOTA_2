package sbu.cs.mahkats.Api;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Server.Unit.Unit;

public class MovableUnitData extends GamePlayData{
    protected int level;
    protected int mana;
    protected int mana_regeneration;
    protected String ability1;
    protected String ability2;
    protected String ability3;
    protected String ability4;

    public MovableUnitData(Long token, double hp, double hp_regeneration, double minimum_damage, double maximum_damage, double armor, double range, double experience, Boolean isAttacking, int defender, boolean isDie, int code, int level, int mana, int mana_regeneration, String ability1, String ability2, String ability3, String ability4) {
        super(token, hp, hp_regeneration, minimum_damage, maximum_damage, armor, range, experience, isAttacking, defender, isDie, code);
        this.level = level;
        this.mana = mana;
        this.mana_regeneration = mana_regeneration;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.ability3 = ability3;
        this.ability4 = ability4;
    }

    public MovableUnitData(Long token, String error) {
        super(token, error);
    }

    @Override
    public JsonObject makeJson() {
        String str = super.makeJson().toString();
        str = str.substring(1 , str.length() - 1) + ", ";
        return new Api().toJson(str + new Api().toJson(new Pair<>("level", level),
                        new Pair<>("experience", experience),
                        new Pair<>("mana", mana),
                        new Pair<>("mana_regeneration", mana_regeneration),
                        new Pair<>("ability1", ability1),
                        new Pair<>("ability2", ability2),
                        new Pair<>("ability3", ability3),
                        new Pair<>("ability4", ability4)).toString());
    }
}
