package sbu.cs.mahkats.Client.Connection;

import com.google.gson.JsonObject;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Api.UserData;
import sbu.cs.mahkats.Configuration.Config;

import java.io.*;
import java.net.Socket;


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

    public static boolean getCheckStatus() {
        return checkStatus;
    }

    public Connection() {
        HOST = config.getStringValue("Host");
        PORT = config.getIntValue("PORT");
        try {
            this.socket = new Socket(HOST, PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            statusConnection = true;
        } catch (IOException e) {
            e.printStackTrace();
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
                return true;
            }
        } catch (Exception e){
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

    public static String receive() {
        String data = null;

        try {
            data = dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
            UserData userData = new UserData("couldn't receive, please send it again!" );
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "receive", userData);
            checkStatus = Parser.getStatus(json);
            if (checkStatus)
                 Long.valueOf(data);
            send(json.toString());
        }
        return data;
    }

}
