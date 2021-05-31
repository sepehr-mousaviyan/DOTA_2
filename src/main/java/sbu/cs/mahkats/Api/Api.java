package sbu.cs.mahkats.Api;

/**
 * Api maker
 */

import com.google.gson.Gson;

public class Api {

    public String toString()  {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }

    public Api toJson(String jsonString) {
        Gson gson = new Gson();
        Api json = gson.fromJson(jsonString, Api.class);
        return json;
    }

    public void send() {
        return;
    }
}
