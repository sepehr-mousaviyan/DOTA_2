package sbu.cs.mahkats.Server.Connection.Client;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Data.*;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Connection.DataBase.DataBase;
import sbu.cs.mahkats.Server.Unit.Building.Tower.Tower;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.KnightHero;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.RangedHero;
import sbu.cs.mahkats.Server.Unit.unitList;
import sbu.cs.mahkats.Server.Unit.Building.Ancient.Ancient;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Server.PlayerData;
import sbu.cs.mahkats.Server.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client {
    private long ThreadID;
    private Socket socket;
    private Thread thread;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private DataBase dataBase;
    private User user;
    public long TOKEN;
    public String teamName;

    private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

    public Client(long ThreadID, Socket socket) {
        this.ThreadID = ThreadID;
        this.socket = socket;
        try {
            this.dataBase = new DataBase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOGGER.fatal("server can not connect DB!",throwables);
        }
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException throwables) {
            LOGGER.fatal("server can not connect to IO client!",throwables);
        }
        user = new User();
    }
    
    public void sendData() {
        unitList greenUnits = GamePlay.getGreenUnits();
        unitList redUnits = GamePlay.getGreenUnits();
        Ancient greenAncient = greenUnits.getAncient();
        Ancient redAncient = redUnits.getAncient();
        ArrayList<Hero> greenHeroes= greenUnits.getHeroes();
        ArrayList<Hero> redHeroes= redUnits.getHeroes();
        ArrayList<Creep> greenCreeps= greenUnits.getCreeps();
        ArrayList<Creep> redCreeps= redUnits.getCreeps();
        ArrayList<Tower> greenTowers= greenUnits.getTowers();
        ArrayList<Tower> redTowers= redUnits.getTowers();
        ArrayList<Barrack> greenBarracks= greenUnits.getBarracks();
        ArrayList<Barrack> redBarracks= redUnits.getBarracks();
        ArrayList<Ability> greenAbility = greenUnits.getHeroes().get(0).getAbilities();
        ArrayList<Ability> redAbility = redUnits.getHeroes().get(0).getAbilities();
        sendAncientData(greenAncient , "ancient" , "ok");
        sendAncientData(redAncient , "ancient" , "ok");
        sendHeroData(greenHeroes, "hero" , "ok");
        sendHeroData(redHeroes, "hero" , "ok");
        sendCreepData(greenCreeps, "creep" , "ok");
        sendCreepData(redCreeps, "creep" , "ok");
        sendTowerData(greenTowers, "tower" , "ok");
        sendTowerData(redTowers, "tower" , "ok");
        sendBarrackData(greenBarracks, "barrack" , "ok");
        sendBarrackData(redBarracks, "barrack" , "ok");
        sendAbilityData(greenAbility, "ability", "ok");
        sendAbilityData(redAbility, "ability", "ok");
        send(new MassageMaker().massage("ok" , "End").toString());
    }

    public void sendAncientData(Ancient ancient, String action, String status){
        BuildingData ancientData = new BuildingData(TOKEN , ancient.getHp(), ancient.getHp_regeneration() ,
                ancient.getMinimum_damage() , ancient.getMaximum_damage() , ancient.getArmor() , ancient.getRange() ,
                ancient.getExperience() , ancient.getStatusAttacker() , 0 ,
                ancient.getStatusDie() , ancient.getCode() , "Ancient", ancient.getTeamName(), ancient.getLocation_x(),
                ancient.getLocation_y());
        send(new MassageMaker().massage(status , action , ancientData).toString());
    }

    public void sendHeroData(ArrayList <Hero> heroes, String action, String status) {
        for(Hero hero : heroes){
            HeroData heroData = makeHeroData(hero);
            send(new MassageMaker().massage(status , action , heroData).toString());
        }

    }

    private HeroData makeHeroData(Hero hero) {
        int defender = 0;
        if(hero.getDefender() != null) {
            defender = hero.getDefender().getCode();
        }
        return new HeroData(TOKEN, hero.getHp(), hero.getHp_regeneration(),
                hero.getMinimum_damage(), hero.getMaximum_damage(), hero.getArmor(), hero.getRange(),
                hero.getExperience(), hero.getStatusAttacker(), defender,
                hero.getStatusDie(), hero.getCode(), hero.getLevel(), hero.getMana(),
                hero.getMana_regeneration(), hero.getAbility1().toString(), hero.getAbility2().toString(),
                hero.getAbility3().toString(), hero.getTeamName(), hero.getLocation_x(),
                hero.getLocation_y(), hero.getHero_name());
    }

    public void sendCreepData(ArrayList <Creep> creeps, String action, String status){
        for(Creep creep : creeps){
            int defender = 0;
            if(creep.getDefender() != null) {
                defender = creep.getDefender().getCode();
            }
            CreepData creepData = new CreepData(TOKEN , creep.getHp(), creep.getHp_regeneration() ,
                creep.getMinimum_damage() , creep.getMaximum_damage() , creep.getArmor() , creep.getRange() ,
                creep.getExperience() , creep.getStatusAttacker() , defender ,
                creep.getStatusDie() , creep.getCode(), creep.getLevel() , creep.getMana() ,
                creep.getMana_regeneration(), creep.getType(), creep.getTeamName(), creep.getLocation_x(),
                    creep.getLocation_y());
            send(new MassageMaker().massage(status , action , creepData).toString());      
        }
    }

    public void sendTowerData(ArrayList <Tower> towers, String action, String status){
        for(Tower tower : towers){
            int defender = 0;
            if(tower.getDefender() != null) {
                defender = tower.getDefender().getCode();
            }
            BuildingData towerData = new BuildingData(TOKEN , tower.getHp(), tower.getHp_regeneration() ,
                tower.getMinimum_damage() , tower.getMaximum_damage() , tower.getArmor() , tower.getRange() ,
                tower.getExperience() , tower.getStatusAttacker() , defender ,
                tower.getStatusDie() , tower.getCode() , tower.getTeamName(), "Tower",
                    tower.getLocation_x(), tower.getLocation_y());
            send(new MassageMaker().massage(status , action , towerData).toString());    
        }
    }

    public void sendBarrackData(ArrayList <Barrack> greenBarracks, String action, String status){
        for(Barrack barrack : greenBarracks){
            BuildingData barrackData = new BuildingData(TOKEN , barrack.getHp(), barrack.getHp_regeneration() ,
                barrack.getMinimum_damage() , barrack.getMaximum_damage() , barrack.getArmor() , barrack.getRange() ,
                barrack.getExperience() , barrack.getStatusAttacker() , 0 ,
                barrack.getStatusDie() , barrack.getCode() , barrack.getTeamName() , "Barrack",
                    barrack.getLocation_x(), barrack.getLocation_y());
            send(new MassageMaker().massage(status , action , barrackData).toString());    
        }
    }

    public void sendAbilityData(ArrayList <Ability> abilities, String action, String status){
        for(Ability ability : abilities){
            AbilityData abilityData = new AbilityData(TOKEN , ability.getNAME(), ability.getUNLOCK_LEVEL() ,
                    ability.getGUNSHOT() , ability.getSTAGE_NUMBERS() , ability.getRange() , ability.getDamage() ,
                    ability.getMANA_COST() , ability.getReloadDuration() , ability.getDuration(),
                    ability.getLeft_duration_turn() , ability.getLeft_duration_reload_turn(),
                    ability.isAvailable() , ability.getStage(), ability.getIsUnlock(),
                    ability.canUnlock(), null , 0 );
            send(new MassageMaker().massage(status , action , abilityData).toString());
        }
    }

    /**
     * this function gets the string of massage json and if can't get correctly massage,
     * it will be send a massage that send again
     * @return string of json massage
     */
    public String receiveUserData() {
        String data = null;
        try {
            data = dataInputStream.readUTF();
            LOGGER.info("message received.");
        } catch (IOException e) {
            UserData userData = new UserData("couldn't receive, please send it again!" );
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "receive", userData);
            this.send(json.toString());
            LOGGER.fatal("message didn't received successfully!", e);
        }
        return data;
    }

    public String receiveData() {
        String data = null;
        try {
            data = dataInputStream.readUTF();
            LOGGER.info("message received.");
        } catch (IOException e) {
            LOGGER.fatal("message didn't received successfully!", e);
        }
        return data;
    }

    /**
     * this function is just for sending
     * @param data
     */
    public void send(String data) {
        try {
            dataOutputStream.writeUTF(data);
            LOGGER.info("message sent.\n" + data);
        } catch (IOException e) {
            LOGGER.fatal("message didn't sent successfully!", e);
        }
    }

    public void handlerLoginSignup() {
        while(true) {
            String data = receiveUserData();
            Api api = new Api();
            JsonObject json = api.toJson(data);
            Pair res = null;
            String username;
            String password;

            switch (Parser.getAction(json)){
                case "signup":
                    username = Parser.parseUserData(json).getUsername();
                    password = Parser.parseUserData(json).getPassword();
                    String email = Parser.parseUserData(json).getEmail();
                    res = dataBase.signupRequest(username, password, email);
                    send(user.ResSignup(res, new PlayerData(username, password), this));
                    return;

                case "signin":
                    username = Parser.parseUserData(json).getUsername();
                    password = Parser.parseUserData(json).getPassword();
                    res = dataBase.loginRequest(username, password);
                    send(user.ResSignin(res, new PlayerData(username, password), this));
                    return;
                    
        
                default:
                    break;
            }

        }

    }

    public void sendListHero(){
        Hero hero = new KnightHero("" , 0);
        HeroData knight = makeHeroData(hero);
        hero = new RangedHero("" , 0);
        HeroData ranged = makeHeroData(hero);
        send(new MassageMaker().massage("ok", "listHero" , 2 , knight , ranged).toString());
    }

    public String getSelectHero(){
        String data = null;
        try {
            do {
                data = dataInputStream.readUTF();
            }while(data == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActionHeroData actionHeroData = Parser.parseActionHeroData(new Api().toJson(data));
        if(actionHeroData.getChoice() == 5){
            return actionHeroData.getHeroName();
        }
        return null;
    }

    public void setTOKEN(long TOKEN) {
        this.TOKEN = TOKEN;
    }

    public void setTeamName(String name){
        teamName = name;
    }

    public String getTeamName(){
        return teamName;
    }
}
