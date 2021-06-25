package sbu.cs.mahkats.Client.Connection;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Api.Data.UserData;
import sbu.cs.mahkats.Configuration.Config;

import java.io.*;
import java.net.Socket;


public class Connection {
    private static Boolean checkStatus = false;
    private static Boolean statusConnection;
    private static Config config = Config.getInstance();
    private static Socket socket;
    private static String HOST;
    private static int PORT;
    private static long TOKEN;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    private static final Logger logger = Logger.getLogger(Connection.class.getName());

    public static boolean getCheckStatus() {
        return checkStatus;
    }

    public static long getTOKEN() {
        return TOKEN;
    }

    public Connection() {
        HOST = config.getStringValue("connection.host");
        PORT = config.getIntValue("connection.port");
        try {
            this.socket = new Socket(HOST, PORT);
            logger.info("connecting to server in connection class ...");
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            statusConnection = true;
        } catch (IOException e) {
            logger.fatal("connection to server failed in connection class", e);
            statusConnection = false;
        }
    }

    public Boolean getStatusConnection(){return statusConnection;}

    public static boolean checkUserSignUp(String userName, String passWord, String email){
        boolean resault = false;

        try {
            UserData user = new UserData(userName,passWord,email);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject signinObj = massageMaker.massage("OK","signup",user);
            if(send(signinObj.toString())){
                logger.info("registering a new user was successful ");
                return true;
            }
        } catch (Exception e){
            logger.fatal("email address or username already exist", e);
        }
        return resault;
    }

    public static boolean checkUserSignIn(String userName, String passWord) throws IOException {
        boolean resault = false;

        try {
            UserData user = new UserData(userName,passWord);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject signinObj = massageMaker.massage("OK","signin",user);
            if(send(signinObj.toString())){
                resault = true;
            }
        } catch (Exception e){
            logger.fatal("Account not found", e);
            throw e;
        }
        return resault;

    }
    public static boolean send(String data) throws IOException {
        boolean resault = false;
        try {
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();
            resault = true;
            logger.info("massage has been sent");
        } catch (IOException e) {
            logger.fatal("Can not send massage", e);
            throw e;
        }
        return resault;
    }

    public static String receive() throws IOException {
        String data = null;
        String error = null;
        try {
            data = dataInputStream.readUTF();
            Api api = new Api();
            JsonObject json = api.toJson(data);
            logger.info("massage has been receive");
            if(!Parser.getStatus(json))
                error = Parser.parseUserData(json).getError();
            else
                TOKEN = Parser.parseUserData(json).getToken();

        } catch (IOException e) {
            logger.fatal("can not receive massage!", e);
            throw e;
        }
        return error;
    }

}
