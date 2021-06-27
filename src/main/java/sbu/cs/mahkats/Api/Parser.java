package sbu.cs.mahkats.Api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Data.*;

import java.util.ArrayList;

public class Parser{
    public static Boolean getStatus(JsonObject json){
        return new Gson().fromJson(json , JsonData.class).getStatus().equals("OK");
    }

    public static String getAction(JsonObject json){
        return new Gson().fromJson(json , JsonData.class).getAction();
    }


    public static UserData parseUserData(JsonObject json) {
        Gson gson = new Gson();
        JsonData userData = gson.fromJson(json , JsonData.class);
        String content = userData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), UserData.class);
    }

    public static HeroData parseHeroData(JsonObject json) {
        Gson gson = new Gson();
        JsonData heroData = gson.fromJson(json , JsonData.class);
        String content = heroData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), HeroData.class);
    }

    public static CreepData parseCreepData(JsonObject json) {
        Gson gson = new Gson();
        JsonData creepData = gson.fromJson(json , JsonData.class);
        String content = creepData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), CreepData.class);
    }

    public static BuildingData parseBuildingData(JsonObject json) {
        Gson gson = new Gson();
        JsonData buildingData = gson.fromJson(json , JsonData.class);
        String content = buildingData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), BuildingData.class);
    }

    public static ArrayList<HeroData> parseListHeroesData(JsonObject json) {
        Gson gson = new Gson();
        JsonData buildingData = gson.fromJson(json , JsonData.class);
        String content = buildingData.getContent().toString();
        ArrayList<Pair<String, JsonObject>> heroes =  gson.fromJson(new Api().toJson(content), ArrayList.class);
        ArrayList<HeroData> heroesData = new ArrayList<>();
        for(Pair<String, JsonObject> pair : heroes){
            heroesData.add(gson.fromJson(new Api().toJson(pair.getValue1().toString()), HeroData.class));
        }
        return heroesData;
    }

    public static AbilityData parseAbilityData(JsonObject json) {
        Gson gson = new Gson();
        JsonData buildingData = gson.fromJson(json , JsonData.class);
        String content = buildingData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), AbilityData.class);
    }

    public static ActionHeroData parseActionHeroData(JsonObject json) {
        Gson gson = new Gson();
        JsonData buildingData = gson.fromJson(json , JsonData.class);
        String content = buildingData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), ActionHeroData.class);
    }
}
