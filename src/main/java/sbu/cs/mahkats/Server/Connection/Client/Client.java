package sbu.cs.mahkats.Server.Connection.Client;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Api.UserData;
import sbu.cs.mahkats.Server.Connection.DataBase.DataBase;
import sbu.cs.mahkats.Server.PlayerData;
import sbu.cs.mahkats.Server.Player;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class Client {
    private long ThreadID;
    private Socket socket;
    private Thread thread;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private DataBase dataBase;
    private Player player;

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
                    send(player.ResSignup(res, new PlayerData(username, password)));
                    break;

                case "signin":
                    username = Parser.parseUserData(json).getUsername();
                    password = Parser.parseUserData(json).getPassword();
                    res = dataBase.loginRequest(username, password);
                    send(player.ResSignin(res, new PlayerData(username, password)));
                    break;

                default:
                    break;
            }

        }

    }
}
