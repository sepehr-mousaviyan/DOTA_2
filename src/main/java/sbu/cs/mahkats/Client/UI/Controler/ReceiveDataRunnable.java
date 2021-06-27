package sbu.cs.mahkats.Client.UI.Controler;

import com.google.gson.JsonObject;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Data.BuildingData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Api.Parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReceiveDataRunnable implements Runnable{
    private final ArrayList<BuildingData> buildings = new ArrayList<>();
    private final ArrayList<HeroData> heroes = new ArrayList<>();
    private final ArrayList<CreepData> creeps = new ArrayList<>();
    private final DataInputStream dataInputStream;

    public ReceiveDataRunnable(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public void run() {
        while(true){
            String message = "";
            try {
                message = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonObject josnMessage = new Api().toJson(message);
            String action = Parser.getAction(josnMessage);
            switch (action){
                case "Building":
                    updateBuildingData(Parser.parseBuildingData(josnMessage));
                    break;
                case "Hero":
                    updateHeroData(Parser.parseHeroData(josnMessage));
                    break;
                case "Creep":
                    updateCreepData(Parser.parseCreepData(josnMessage));
                    break;
                case "End":
                    //TODO: refresh the game
                    MapController.checkUnits(heroes,creeps,buildings);
                    break;
            }
        }
    }

    public void updateBuildingData(BuildingData buildingData){
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i).getCode() == buildingData.getCode()) {
                buildings.remove(buildings.get(i));
            }
        }
        buildings.add(buildingData);
    }

    public void updateHeroData(HeroData heroData){
        for(int i = 0 ; i < heroes.size() ; i++){
            if(heroes.get(i).getCode() == heroData.getCode()) {
                heroes.remove(heroes.get(i));
            }
        }
        heroes.add(heroData);
    }

    public void updateCreepData(CreepData creepData){
        for(int i = 0 ; i < creeps.size() ; i++){
            if(creeps.get(i).getCode() == creepData.getCode()) {
                creeps.remove(creeps.get(i));
            }
        }
        creeps.add(creepData);
    }

}
