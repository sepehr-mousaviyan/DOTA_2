package sbu.cs.mahkats.Api;

import com.google.gson.JsonObject;
import org.javatuples.Pair;
import sbu.cs.mahkats.Server.Model.Unit.Building.Building;
import sbu.cs.mahkats.Server.Model.Unit.Movable.Movable;

public class GamePlayData extends Data{
    private final Long token;
    private Movable movable;
    private Building building;
    private String error;

    public GamePlayData(Long token, Movable movable) {
        this.token = token;
        this.movable = movable;
    }

    public GamePlayData(Long token, Building building) {
        this.token = token;
        this.building = building;
    }

    public GamePlayData(Long token, String error) {
        this.token = token;
        this.error = error;
    }

    public Long getToken() {
        return token;
    }

    public Movable getMovable() {
        return movable;
    }

    public Building getBuilding() {
        return building;
    }

    public String getError() {
        return error;
    }

    public JsonObject makeJson(){
        if(error == null) {
            return new Api().toJson(new Pair<>("token", token),
                    new Pair<>("info_hero:", movable.toString()));
        }
        return new Api().toJson(new Pair<>("error", error), new Pair<>("token", token));
    }
}
