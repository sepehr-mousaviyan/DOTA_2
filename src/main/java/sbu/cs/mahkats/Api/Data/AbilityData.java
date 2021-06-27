package sbu.cs.mahkats.Api.Data;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;

import java.util.ArrayList;

public class AbilityData implements Data{
    private long token;
    private String name;
    private int unlock_level;
    private int gunShot;
    private int max_stage;
    private int range;
    private double damage;
    private int manaCost;
    private int reloadDuration;
    private int duration;
    private int left_duration_turn;
    private int left_duration_reload_turn;
    private boolean isAvailable;
    private int stage;
    private boolean isUnlock;
    private boolean canUnlock;
    private ArrayList<Integer> defendersCode;
    private int heroCode;
    private String error;

    public AbilityData(long token, String name, int unlock_level, int gunShot, int max_stage, int range, double damage, int manaCost, int reloadDuration, int duration, int left_duration_turn, int left_duration_reload_turn, boolean isAvailable, int stage, boolean isUnlock, boolean canUnlock, ArrayList<Integer> defendersCode , int heroCode) {
        this.token = token;
        this.name = name;
        this.unlock_level = unlock_level;
        this.gunShot = gunShot;
        this.max_stage = max_stage;
        this.range = range;
        this.damage = damage;
        this.manaCost = manaCost;
        this.reloadDuration = reloadDuration;
        this.duration = duration;
        this.left_duration_turn = left_duration_turn;
        this.left_duration_reload_turn = left_duration_reload_turn;
        this.isAvailable = isAvailable;
        this.stage = stage;
        this.isUnlock = isUnlock;
        this.canUnlock = canUnlock;
        this.defendersCode = defendersCode;
        this.heroCode = heroCode;
    }

    public AbilityData(long token, String error) {
        this.token = token;
        this.error = error;
    }

    @Override
    public JsonObject makeJson() {
        if(error != null) {
            return new Api().toJson(new Pair<>("token", token),
                    new Pair<>("name", name),
                    new Pair<>("unlock_level", unlock_level),
                    new Pair<>("gunShot", gunShot),
                    new Pair<>("max_stagr", max_stage),
                    new Pair<>("damage", damage),
                    new Pair<>("range", range),
                    new Pair<>("manaCost", manaCost),
                    new Pair<>("reloadDuration", reloadDuration),
                    new Pair<>("duration", duration),
                    new Pair<>("left_duration_turn", left_duration_turn),
                    new Pair<>("left_duration_reload_turn", left_duration_reload_turn),
                    new Pair<>("isAvailable", isAvailable),
                    new Pair<>("stage", stage),
                    new Pair<>("isUnlock", isUnlock),
                    new Pair<>("canUnlock", canUnlock),
                    new Pair<>("canUnlock", canUnlock),
                    new Pair<>("defenders", defendersCode));
        }
        return new Api().toJson(new Pair<>("error", error), new Pair<>("token", token));
    }
}
