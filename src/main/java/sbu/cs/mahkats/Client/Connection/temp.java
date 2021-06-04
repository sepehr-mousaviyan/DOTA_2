package sbu.cs.mahkats.Client.Connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import sbu.cs.mahkats.Api.Api;


public class temp {
    public static void main(String[] args) {
        Api api = new Api();
        JsonObject jsonObjectSignup = api.toJson("userName");
        System.out.println(jsonObjectSignup.getAsJsonArray());
    }
}