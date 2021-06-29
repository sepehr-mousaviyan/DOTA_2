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
import sbu.cs.mahkats.Server.Unit.Movable.Hero.KnightHero;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.RangedHero;
import sbu.cs.mahkats.Server.Unit.unitList;
import sbu.cs.mahkats.Server.Unit.Building.Ancient.Ancient;
import sbu.cs.mahkats.Server.Unit.Building.Barrack.Barrack;
import sbu.cs.mahkats.Server.Unit.Movable.Creep.Creep;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Server.PlayerData;
import sbu.cs.mahkats.Server.Player;

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
    private Player player;
    public long TOKEN;

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
        player = new Player();
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
        send(new MassageMaker().massage("ok" , "End").toString());
    }

    public void sendAncientData(Ancient ancient, String action, String status){
        BuildingData ancientData = new BuildingData(TOKEN , ancient.getHp(), ancient.getHp_regeneration() ,
                ancient.getMinimum_damage() , ancient.getMaximum_damage() , ancient.getArmor() , ancient.getRange() ,
                ancient.getExperience() , ancient.getStatusAttacker() , ancient.getDefender().getCode() ,
                ancient.getStatusDie() , ancient.getCode() , "Ancient", ancient.getTeamName() );
        send(new MassageMaker().massage(status , action , ancientData).toString());
    }

    public void sendHeroData(ArrayList <Hero> heroes, String action, String status) {
        for(Hero hero : heroes){
            HeroData heroData = makeHeroData(hero);
            send(new MassageMaker().massage(status , action , heroData).toString());
        }

    }

    private HeroData makeHeroData(Hero hero) {
        return new HeroData(TOKEN , hero.getHp(), hero.getHp_regeneration() ,
            hero.getMinimum_damage() , hero.getMaximum_damage() , hero.getArmor() , hero.getRange() ,
            hero.getExperience() , hero.getStatusAttacker() , hero.getDefender().getCode() ,
            hero.getStatusDie() , hero.getCode(), hero.getLevel() , hero.getMana() ,
            hero.getMana_regeneration() , hero.getAbility1().toString() , hero.getAbility2().toString() ,
            hero.getAbility3().toString() , hero.getTeamName() );
    }

    public void sendCreepData(ArrayList <Creep> creeps, String action, String status){
        for(Creep creep : creeps){
            CreepData creepData = new CreepData(TOKEN , creep.getHp(), creep.getHp_regeneration() ,
                creep.getMinimum_damage() , creep.getMaximum_damage() , creep.getArmor() , creep.getRange() ,
                creep.getExperience() , creep.getStatusAttacker() , creep.getDefender().getCode() ,
                creep.getStatusDie() , creep.getCode(), creep.getLevel() , creep.getMana() ,
                creep.getMana_regeneration(), creep.getType(), creep.getTeamName());
            send(new MassageMaker().massage(status , action , creepData).toString());      
        }
    }

    public void sendTowerData(ArrayList <Tower> towers, String action, String status){
        for(Tower tower : towers){
            BuildingData towerData = new BuildingData(TOKEN , tower.getHp(), tower.getHp_regeneration() ,
                tower.getMinimum_damage() , tower.getMaximum_damage() , tower.getArmor() , tower.getRange() ,
                tower.getExperience() , tower.getStatusAttacker() , tower.getDefender().getCode() ,
                tower.getStatusDie() , tower.getCode() , tower.getTeamName(), "Tower");
            send(new MassageMaker().massage(status , action , towerData).toString());    
        }
    }

    public void sendBarrackData(ArrayList <Barrack> greenBarracks, String action, String status){
        for(Barrack barrack : greenBarracks){
            BuildingData barrackData = new BuildingData(TOKEN , barrack.getHp(), barrack.getHp_regeneration() ,
                barrack.getMinimum_damage() , barrack.getMaximum_damage() , barrack.getArmor() , barrack.getRange() ,
                barrack.getExperience() , barrack.getStatusAttacker() , barrack.getDefender().getCode() ,
                barrack.getStatusDie() , barrack.getCode() , barrack.getTeamName() , "Barrack");
            send(new MassageMaker().massage(status , action , barrackData).toString());    
        }
    }

    /**
     *this function gets the string of massage json and if can't get correctly massage,
     * it will be send a massage that send again
     * @return string of json massage
     */
    public String receive() {
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
            LOGGER.info("message sent.");
        } catch (IOException e) {
            LOGGER.fatal("message didn't sent successfully!", e);
        }
    }

    public void handler() {
        while(true) {
            String data = receive();
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
                    send(player.ResSignup(res, new PlayerData(username, password), this));
                    return;

                case "signin":
                    username = Parser.parseUserData(json).getUsername();
                    password = Parser.parseUserData(json).getPassword();
                    res = dataBase.loginRequest(username, password);
                    send(player.ResSignin(res, new PlayerData(username, password), this));
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
        String data = "";
        try {
            data = dataInputStream.readUTF();
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
}
