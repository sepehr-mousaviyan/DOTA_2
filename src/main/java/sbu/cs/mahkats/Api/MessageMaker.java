package sbu.cs.mahkats.Api;


import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Data.*;

import java.util.HashMap;

public class MessageMaker {

    public static JsonObject message(String status, String action, UserData user) {
        Pair<String, String> statusProperty = new Pair<>("status", status);
        Pair<String, String> actionProperty = new Pair<>("action", action);
        Pair<String, JsonObject> contentProperty;
        if (status.equals("OK")) {
            contentProperty = new Pair<>("content", user.makeJson());
        } else {
            contentProperty = new Pair<>("content", user.makeErrorJson());
        }

        return Api.toJson(statusProperty, actionProperty, contentProperty);
    }

    public static JsonObject message(String status, String action, GamePlayData gamePlayData) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action),
                new Pair<>("content", gamePlayData.makeJson()));
    }

    public static JsonObject message(String status, String action, BuildingData buildingData) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action),
                new Pair<>("content", buildingData.makeJson()));
    }

    public static JsonObject message(String status, String action, CreepData creepData) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action),
                new Pair<>("content", creepData.makeJson()));
    }

    public static JsonObject message(String status, String action, HeroData heroData) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action),
                new Pair<>("content", heroData.makeJson()));
    }

    public static JsonObject message(String status, String action, int number, HeroData... heroData) {
        Pair<String, String> statusProperties = new Pair<>("status", status);
        Pair<String, String> actionProperties = new Pair<>("action", action);
        HashMap<String, JsonObject> heroList = new HashMap<>();
        for (int i = 0; i < number; i++) {
            heroList.put("hero" + (i + 1), heroData[i].makeJson());
        }
        Pair contentProperties = new Pair<>("content", Api.toJson(heroList.toString()));
        return Api.toJson(statusProperties, actionProperties, contentProperties);
    }

    public static JsonObject message(String status, String action, ActionHeroData actionHeroData) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action),
                new Pair<>("content", actionHeroData.makeJson()));
    }

    public static JsonObject message(String status, String action, AbilityData abilityData) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action),
                new Pair<>("content", abilityData.makeJson()));
    }

    public static JsonObject message(String status, String action) {
        return Api.toJson(new Pair<>("status", status),
                new Pair<>("action", action));
    }
}
