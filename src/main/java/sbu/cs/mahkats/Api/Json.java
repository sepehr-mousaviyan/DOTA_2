package sbu.cs.mahkats.Api;

import com.google.gson.Gson;

public class Json {

    public String toString()  {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }

    public Json toJson(String jsonString) {
        Gson gson = new Gson();
        Json json = gson.fromJson(jsonString, Json.class);
        return json;
    }

    public void send() {
        return;
    }
}
