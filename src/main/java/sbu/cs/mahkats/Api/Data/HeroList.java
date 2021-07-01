package sbu.cs.mahkats.Api.Data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class HeroList {
    private JsonObject hero1;
    private JsonObject hero2;

    public ArrayList<HeroData> getHeroes() {
        ArrayList<HeroData> heroes = new ArrayList<>();
        heroes.add(new Gson().fromJson(hero1, HeroData.class));
        heroes.add(new Gson().fromJson(hero2, HeroData.class));
        return heroes;
    }
}
