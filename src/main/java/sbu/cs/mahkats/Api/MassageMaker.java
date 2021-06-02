package sbu.cs.mahkats.Api;

import com.google.gson.JsonObject;
import org.javatuples.Pair;

public class MassageMaker {
    public static JsonObject Massage(String status, String action, JsonObject content){
        Pair<String, String> statusProperty = new Pair<>("status" , status);
        Pair<String, String> actionProperty = new Pair<>("action" , action);
        Pair<String, JsonObject> contentProperty = new Pair<>("content" , content);

        return new Api().toJson(statusProperty, actionProperty, contentProperty);
    }
}
