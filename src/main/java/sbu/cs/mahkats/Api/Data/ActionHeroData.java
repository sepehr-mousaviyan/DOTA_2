package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class ActionHeroData implements Data{
    private final long token;
    private String heroName = "";
    private int heroCode = 0;
    private int abilityName = 0;
    private int defenderCode = 0;
    private int location_x = 0;
    private int location_y = 0;
    /**
     * possible of choice:
     * 1- move hero
     * 2- add new ability
     * 3- update ability
     * 4- attack the unit
     * 5- select the hero
     * 6- use ability
     */
    private int choice;

    public ActionHeroData(long token, String heroName, int heroCode, int abilityName, int defenderCode, int choice) {
        this.token = token;
        this.heroName = heroName;
        this.heroCode = heroCode;
        this.abilityName = abilityName;
        this.defenderCode = defenderCode;
        this.choice = choice;
    }

    public ActionHeroData(long token, int heroCode , int location_x, int location_y, int choice) {
        this.token = token;
        this.heroCode = heroCode;
        this.location_x = location_x;
        this.location_y = location_y;
        this.choice = choice;
    }

    public long getToken() {
        return token;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHeroCode() {
        return heroCode;
    }

    public int getAbilityName() {
        return abilityName;
    }

    public int getDefenderCode() {
        return defenderCode;
    }

    public int getLocation_x() {
        return location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public int getChoice() {
        return choice;
    }

    @Override
    public JsonObject makeJson() {
        if (location_x == 0 || location_y == 0){
            return new Api().toJson(new Pair<>("token", token),
                    new Pair<>("heroName", heroName),
                    new Pair<>("heroCode", heroCode),
                    new Pair<>("abilityName", abilityName),
                    new Pair<>("defenderCode", defenderCode),
                    new Pair<>("choice", choice));
        }
        return new Api().toJson(new Pair<>("token", token),
                new Pair<>("heroCode", heroCode),
                new Pair<>("location_x", location_x),
                new Pair<>("location_y", location_y));
    }
}
