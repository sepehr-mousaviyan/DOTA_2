package sbu.cs.mahkats.Api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

    public static GamePlayData parseGamePlayData(JsonObject json) {
        Gson gson = new Gson();
        JsonData gamePlayData = gson.fromJson(json , JsonData.class);
        String content = gamePlayData.getContent().toString();
        return gson.fromJson(new Api().toJson(content), GamePlayData.class);
    }
}
