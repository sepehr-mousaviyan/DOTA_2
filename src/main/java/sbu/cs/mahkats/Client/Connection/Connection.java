package sbu.cs.mahkats.Client.Connection;

import com.google.gson.JsonObject;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Configuration.Config;

import java.io.IOException;
import java.net.Socket;


public class Connection {
    Config config = Config.getInstance();
    private Socket socket;
    private static String Host = "host";
    private static String PORT = "port";

    Api api = new Api();

    public Connection() throws IOException {
        this.socket = new Socket(config.getStringValue(Host
        ),config.getIntValue(PORT));
    }

    public void start(){
    }

    public static boolean checkUserlogIn(String userName, String passWord){
        boolean resault = true;


        return resault;
    }
    public static boolean checkUserSignUp(String username, String passWord, String email){
        boolean resault = false;

        return resault;
    }

}
