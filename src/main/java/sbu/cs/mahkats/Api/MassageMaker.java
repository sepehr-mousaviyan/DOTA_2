package sbu.cs.mahkats.Api;


import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Data.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MassageMaker{

    public JsonObject massage(String status, String action, UserData user){
        Pair<String, String> statusProperty = new Pair<>("status" , status);
        Pair<String, String> actionProperty = new Pair<>("action" , action);
        Pair<String, JsonObject> contentProperty;
        if(status.equals("OK")) {
            contentProperty = new Pair<>("content", user.makeJson());
        }
        else{
            contentProperty = new Pair<>("content", user.makeErrorJson());
        }

        return new Api().toJson(statusProperty, actionProperty, contentProperty);
    }

    public JsonObject massage(String status, String action, GamePlayData gamePlayData){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action),
                new Pair<>("content", gamePlayData.makeJson()));
    }

    public JsonObject massage(String status, String action, BuildingData buildingData){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action),
                new Pair<>("content", buildingData.makeJson()));
    }

    public JsonObject massage(String status, String action, CreepData creepData){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action),
                new Pair<>("content", creepData.makeJson()));
    }

    public JsonObject massage(String status, String action, HeroData heroData){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action),
                new Pair<>("content", heroData.makeJson()));
    }

    public JsonObject massage(String status, String action, int number, HeroData ...heroData){
        Pair<String, String> statusProperties = new Pair<>("status" , status);
        Pair<String, String> actionProperties = new Pair<>("action" , action);
        HashMap<String, JsonObject> heroList = new HashMap<>();
        for(int i = 0 ; i < number; i++){
            heroList.put("hero" + (i+1), heroData[i].makeJson());
        }
        Pair contentProperties = new Pair<>("content", new Api().toJson(heroList.toString()));
        return new Api().toJson(statusProperties, actionProperties, contentProperties);
    }

    public JsonObject massage(String status, String action, AbilityData abilityData){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action),
                new Pair<>("content", abilityData.makeJson()));
    }

    public JsonObject massage(String status, String action){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action));
    }
}
