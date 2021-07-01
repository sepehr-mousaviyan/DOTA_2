package sbu.cs.mahkats.Client.Connection;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Data.AbilityData;
import sbu.cs.mahkats.Api.Data.ActionHeroData;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Api.Data.UserData;
import sbu.cs.mahkats.Api.MessageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Client.UI.Controler.ChooseHeroController;
import sbu.cs.mahkats.Client.UI.Controler.ReceiveDataRunnable;
import sbu.cs.mahkats.Client.Util.HashGenerator;
import sbu.cs.mahkats.Configuration.Config;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class Connection {
    private static Boolean checkStatus = false;
    private static Boolean statusConnection;
    private static final ArrayList<String> BufferMessage = new ArrayList<>();
    private static Socket socket;
    private static String HOST;
    private static int PORT;
    private static long TOKEN;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static final Logger logger = Logger.getLogger(Connection.class.getName());
    private static Config config;

    public Connection() {
        config = Config.getInstance();
        HOST = config.getStringValue("connection.host");
        PORT = config.getIntValue("connection.port");
        try {
            socket = new Socket(HOST, PORT);
            logger.info("connecting to server in connection class ...");
            dataInputStream  = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            statusConnection = true;
        } catch (IOException e) {
            logger.fatal("connection to server failed in connection class", e);
            statusConnection = false;
        }
    }
    public static boolean send(String data) throws IOException {
        try {
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();
            logger.info("massage has been sent\n" + data);
            return true;
        } catch (IOException e) {
            logger.fatal("Can not send massage", e);
            throw e;
        }
    }

    public static boolean checkUserSignUp(String userName, String passWord, String email){
        try {
            String HashedPassWord = HashGenerator.generate(passWord);
            UserData user = new UserData(userName,HashedPassWord,email);
            JsonObject signinObj = MessageMaker.message("OK","signup",user);
            if(send(signinObj.toString())){
                logger.info("registering a new user was successful ");
                receiveSignUpSignIn();
                checkStatus = true;
                return true;
            }
        } catch (Exception e){
            logger.fatal("email address or username already exist", e);
        }
        return checkStatus;
    }

    public static void runReceiver(){
        new Thread(new ReceiveDataRunnable(dataInputStream)).start();
    }

    public static boolean checkUserSignIn(String userName, String passWord) throws IOException, InterruptedException {
        checkStatus = false;
        try {
            String HashedPassWord = HashGenerator.generate(passWord);
            UserData user = new UserData(userName,HashedPassWord);
            MessageMaker messageMaker = new MessageMaker();
            JsonObject signinObj = messageMaker.message("OK","signin",user);
            if(send(signinObj.toString())){
                receiveSignUpSignIn();
                checkStatus = true;
                return true;
            }
        } catch (IOException e){
            logger.fatal("Account not found", e);
            throw e;
        }
        return checkStatus;

    }

    public static String receiveSignUpSignIn() throws IOException {
        String data = null;
        String error = null;
        try {
            data = dataInputStream.readUTF();
            Api api = new Api();
            JsonObject json = Api.toJson(data);
            logger.info("massage has been receive");
            logger.info(data);
            if (!Parser.getStatus(json))
                error = Parser.parseUserData(json).getError();
            else
                TOKEN = Parser.parseUserData(json).getToken();
            checkStatus = true;
        } catch (IOException e) {
            logger.fatal("can not receive massage!", e);
            throw e;
        }
        return error;
    }

    public static void getHeroesData() {
        String data;
        try {
            data = dataInputStream.readUTF();
            ChooseHeroController.setHeroes(Parser.parseListHeroesData(Api.toJson(data)));
            checkStatus = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean getTeamName(){
        String data;
        try {
            data = dataInputStream.readUTF();
            ReceiveDataRunnable.setTeamName(Parser.getAction(Api.toJson(data)));
            statusConnection = true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void sendSelectedHero(String hero_name){
        ActionHeroData actionHeroData = new ActionHeroData(TOKEN, hero_name , 0, "", 0, 5, ReceiveDataRunnable.getTeamName());
        try {
            send(MessageMaker.message("Ok", "actionHero", actionHeroData).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendHeroAction(char ch){
        BufferMessage.add(MessageMaker.message("ok", "actionHero",
                new ActionHeroData(TOKEN, 0, ch, 1)).toString());
    }

    public static void sendNewAbility(AbilityData abilityData, HeroData heroData){
        ActionHeroData actionHeroData = new ActionHeroData(TOKEN, heroData.getHeroType(), heroData.getCode(),abilityData.getName(),0,2, ReceiveDataRunnable.getTeamName());
        BufferMessage.add(MessageMaker.message("Ok", "actionHero", actionHeroData).toString());
    }

    public static void senBufferMessage(){
        if(BufferMessage.size()==0){
            try {
                send(new ActionHeroData(TOKEN, 0, ' ' , 7).makeJson().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            for(int i = 0 ; i < BufferMessage.size() && i < 2 ; i++){
                try {
                    send(BufferMessage.get(i));
                    BufferMessage.remove(BufferMessage.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendUpgradeAbility(AbilityData abilityData, HeroData heroData){
        ActionHeroData actionHeroData = new ActionHeroData(TOKEN, heroData.getHeroType(), heroData.getCode(),abilityData.getName(),0,3, ReceiveDataRunnable.getTeamName());
        BufferMessage.add(MessageMaker.message("Ok", "actionHero", actionHeroData).toString());
    }

    public static void sendUseAbility(AbilityData abilityData, HeroData heroData){
        ActionHeroData actionHeroData = new ActionHeroData(TOKEN, heroData.getHeroType(), heroData.getCode(),abilityData.getName(),0,6,  ReceiveDataRunnable.getTeamName());
        BufferMessage.add(MessageMaker.message("Ok", "actionHero", actionHeroData).toString());
    }

    public static Boolean getStatus() {
        return checkStatus;
    }

    public static boolean getCheckStatus() {
        return statusConnection;
    }

    public static long getTOKEN() {
        return TOKEN;
    }
}
