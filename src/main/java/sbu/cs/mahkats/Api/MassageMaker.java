package sbu.cs.mahkats.Api;


import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Data.*;

import java.util.ArrayList;

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

    public JsonObject massage(String status, String action){
        return new Api().toJson(new Pair<>("status" , status),
                new Pair<>("action" , action));
    }

    public JsonObject massage(String status, String action, int number, HeroData ...heroData){
        Pair<String, String> statusProperties = new Pair<>("status" , status);
        Pair<String, String> actionProperties = new Pair<>("action" , action);
        ArrayList<Pair<String, JsonObject>> heroList = new ArrayList<>();
        for(int i = 0 ; i < number; i++){
            heroList.add(new Pair<>("hero" + i, heroData[i].makeJson()));
        }
        Pair contentProperties = new Pair<String, Pair>("content" , new Pair("heloList" , heroList));
        return new Api().toJson(statusProperties, actionProperties, contentProperties);
    }
}
