package sbu.cs.mahkats.Api;


import com.google.gson.JsonObject;
import org.javatuples.Pair;

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
}
