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
    Config config = Config.getInstance();
    private static Socket socket;
    private static String Host = "host";
    private static String PORT = "port";
    private static long TOKEN;

    public static boolean getCheckStatus() {
        return checkStatus;
    }

    public Connection() throws IOException {
        this.socket = new Socket(config.getStringValue(Host),config.getIntValue(PORT));
    }
    public void start(){
    }

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
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return resault;

    }
    public static boolean send(String data) {
        boolean resault = false;
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resault;
    }

    public static String receive() {
        String data = null;

        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            data = dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
            UserData userData = new UserData("couldn't receive, please send it again!" );
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "receive", userData);
            checkStatus = Parser.getStatus(json);
            if (checkStatus)
                TOKEN = Long.valueOf(data);
            send(json.toString());
        }
        return data;
    }

}
