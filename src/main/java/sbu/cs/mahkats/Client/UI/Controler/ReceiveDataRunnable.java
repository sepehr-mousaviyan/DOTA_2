package sbu.cs.mahkats.Client.UI.Controler;

import com.google.gson.JsonObject;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Data.AbilityData;
import sbu.cs.mahkats.Api.Data.BuildingData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Client.Connection.Connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReceiveDataRunnable implements Runnable{
    private static final ArrayList<BuildingData> buildings = new ArrayList<>();
    private static final ArrayList<HeroData> heroes = new ArrayList<>();
    private static final ArrayList<CreepData> creeps = new ArrayList<>();
    private static final ArrayList<AbilityData> abilities = new ArrayList<>();
    private final DataInputStream dataInputStream;
    private static String teamName = " ";
    private static boolean isReloaded = true;

    public ReceiveDataRunnable(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
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
    public void updateAbilityData(AbilityData abilityData){
        for(int i = 0 ; i < abilities.size() ; i++){
            if(abilities.get(i).getName().equals(abilityData.getName())){
                abilities.remove(abilities.get(i));
            }
        }
        abilities.add(abilityData);
    }

    public  static ArrayList<BuildingData> getBuildings() {
        return buildings;
    }

    public static ArrayList<HeroData> getHeroes() {
        return heroes;
    }

    public static ArrayList<CreepData> getCreeps() {
        return creeps;
    }

    public static ArrayList<AbilityData> getAbilities() {
        return abilities;
    }

    public static void setIsReloaded(boolean isReloaded) {
        ReceiveDataRunnable.isReloaded = isReloaded;
    }

    public static boolean isIsReloaded() {
        return isReloaded;
    }

    public static String getTeamName() {
        return teamName;
    }

    public static void setTeamName(String teamName) {
        ReceiveDataRunnable.teamName = teamName;
    }

    @Override
    public void run() {
        //MapController mapController = new MapController();

        while(true){
            String message = "";
            try {
                message = dataInputStream.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonObject josnMessage = Api.toJson(message);
            if(josnMessage == null){
                continue;
            }
            String action = Parser.getAction(josnMessage);
            switch (action){
                case "building":
                    updateBuildingData(Parser.parseBuildingData(josnMessage));
                    break;
                case "hero":
                    updateHeroData(Parser.parseHeroData(josnMessage));
                    break;
                case "creep":
                    updateCreepData(Parser.parseCreepData(josnMessage));
                    break;
                case "ability" :
                    updateAbilityData(Parser.parseAbilityData(josnMessage));
                    break;
                case "End":
                    Connection.sendHeroAction(MapController.getMove());
                    //mapController.checkUnits(heroes, abilities, creeps, buildings);
                    isReloaded = false;
                    break;
                case "GREEN" :
                    //mapController.setFinished("Green");
                    break;
                case "RED" :
                    //mapController.setFinished("RED");
                    break;
                case "startTurn":
                    Connection.senBufferMessage();
                    break;

            }
        }
    }
}
