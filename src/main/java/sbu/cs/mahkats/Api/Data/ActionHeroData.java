package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

public class ActionHeroData implements Data{
    private final long token;
    private String teamName = "";
    private String heroName = "";
    private int heroCode = 0;
    private String abilityName = "";
    private int defenderCode = 0;
    private int location_x = 0;
    private int location_y = 0;
    private char move = ' ';
    private int[] defendersCode = null;
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

    public ActionHeroData(long token, String heroName, int heroCode, String abilityName, int defenderCode, int choice, String teamName) {
        this.token = token;
        this.heroName = heroName;
        this.heroCode = heroCode;
        this.abilityName = abilityName;
        this.defenderCode = defenderCode;
        this.choice = choice;
        this.teamName = teamName;
    }

//    public ActionHeroData(long token, String heroName, int heroCode, int abilityName, int[] defendersCode, int choice) {
//        this.token = token;
//        this.heroName = heroName;
//        this.heroCode = heroCode;
//        this.abilityName = abilityName;
//        this.defendersCode = defendersCode;
//        this.choice = choice;
//    }

    public ActionHeroData(long token, int heroCode , char move , int choice) {
        this.token = token;
        this.heroCode = heroCode;
        this.move = move;
        this.choice = choice;
    }

    public String getTeamName() {
        return teamName;
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

    public String getAbilityName() {
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

    public int[] getDefendersCode() {
        return defendersCode;
    }

    public char getMove(){
        return move;
    }

    @Override
    public JsonObject makeJson() {
        if (move == ' '){
            if(defendersCode == null) {
                return new Api().toJson(new Pair<>("token", token),
                        new Pair<>("heroName", heroName),
                        new Pair<>("heroCode", heroCode),
                        new Pair<>("abilityName", abilityName),
                        new Pair<>("defenderCode", defenderCode),
                        new Pair<>("choice", choice),
                        new Pair<>("teamName", teamName));
            }
            return new Api().toJson(new Pair<>("token", token),
                    new Pair<>("heroName", heroName),
                    new Pair<>("heroCode", heroCode),
                    new Pair<>("abilityName", abilityName),
                    new Pair<>("defendersCode", defendersCode),
                    new Pair<>("choice", choice),
                    new Pair<>("teamName", teamName));
        }
        return new Api().toJson(new Pair<>("token", token),
                new Pair<>("heroCode", heroCode),
                new Pair<>("move", move),
                new Pair<>("choice", choice),
                new Pair<>("teamName", teamName));
    }
}
