package sbu.cs.mahkats.Api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.javatuples.Pair;

public final class Api {

    public static JsonObject toJson(Pair... args) {
        JsonObject json = new JsonObject();
        for (int i = 0; i < args.length; i++) {
            json.addProperty(args[i].getValue0().toString(), args[i].getValue1().toString());
        }
        return json;
    }

    public static JsonObject toJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonObject.class);
    }

    public static String toString(JsonObject json) {
        return json.toString();
    }

}
