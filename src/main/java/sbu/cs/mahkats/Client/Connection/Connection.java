package sbu.cs.mahkats.Client.Connection;

import com.google.gson.JsonObject;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Api.UserData;
import sbu.cs.mahkats.Configuration.Config;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection {
    private static Boolean checkStatus;
    private static Boolean statusConnection;
    private static Config config = Config.getInstance();
    private static Socket socket;
    private static String HOST;
    private static int PORT;
    private static long TOKEN;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    static Logger logger =  Logger.getLogger(Connection.class.getName());

    public static boolean getCheckStatus() {
        return checkStatus;
    }

    public static long getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(long TOKEN) {
        Connection.TOKEN = TOKEN;
    }

    public Connection() {
        HOST = config.getStringValue("Host");
        PORT = config.getIntValue("PORT");
        try {
            this.socket = new Socket(HOST, PORT);
            logger.log(Level.INFO,"connecting to server in connection class ...");
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            statusConnection = true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.FINER,"connection to server failed in connection class");
            statusConnection = false;
        }
    }

    public Boolean getStatusConnection(){return statusConnection;}

    public static boolean checkUserSignUp(String userName, String passWord, String email){
        boolean resault = false;

        try {
            UserData user = new UserData(userName,passWord,email);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject signinObj = massageMaker.massage("OK","signin",user);
            if(send(signinObj.toString())){
                logger.log(Level.INFO,"registering a new user was successful ");
                return true;
            }
        } catch (Exception e){
            logger.log(Level.FINER,"'email address or username already exist' error in connection class in checkUserSignUp method ");
            e.printStackTrace();
        }
        return resault;
    }

    public static boolean checkUserSignIn(String userName, String passWord) {
        boolean resault = false;

        try {
            UserData user = new UserData(userName,passWord);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject signinObj = massageMaker.massage("OK","signin",user);
            if(send(signinObj.toString())){
                resault = true;
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.log(Level.FINER,"'Account not found' error in sign in part in connection class in checkUserSignIn method " );
        }
        return resault;

    }
    public static boolean send(String data) {
        boolean resault = false;
        try {
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();
            resault = true;
        } catch (IOException e) {
            e.printStackTrace();
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

            if(!Parser.getStatus(json).equals("OK"))
                error = Parser.parse(json).getError();

            else
                TOKEN = Parser.parse(json).getToken();


        } catch (IOException e) {
            throw e;
        }
        return error;
    }

}
