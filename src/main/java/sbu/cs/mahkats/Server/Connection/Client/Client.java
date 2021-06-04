package sbu.cs.mahkats.Server.Connection.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Parser;
import sbu.cs.mahkats.Api.UserData;
import sbu.cs.mahkats.Server.Connection.DataBase.DataBase;
import sbu.cs.mahkats.Server.Player;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    private final long ThreadID;
    private String username;
    private String password;
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
        player = new Player();
        handler();
    }

    public String receive() {
        String data = null;

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            data = dataInputStream.readUTF();
            LOGGER.info("message received.");
        } catch (IOException e) {
            e.printStackTrace();
            UserData userData = new UserData("couldn't receive, please send it again!" );
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "receive", userData);
            this.send(json.toString());
            LOGGER.fatal("message didn't received successfully.");
        }
        return data;
    }

    public void send(String data) {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(data);
            LOGGER.info("message sent.");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.fatal("message didn't sent successfully.");
        }
        return;
    }

    public void handler() {
        while(true) {
            String data = receive();
            Api api = new Api();
            JsonObject json = api.toJson(data);
            Pair res = null;

            switch (Parser.getAction(json)){
                case "signup":
                    res = dataBase.signupRequest(Parser.parse(json).getUsername()
                            , Parser.parse(json).getPassword(), Parser.parse(json).getEmail());
                    player.ResSignup(res, this);
                    break;

                case "signin":
                    try {
                        res = dataBase.loginRequest(Parser.parse(json).getUsername()
                                , Parser.parse(json).getPassword());
                    } catch (SQLException throwables) {
                        LOGGER.fatal("couldn't sign in" , throwables);
                        throwables.printStackTrace();
                    }
                    player.ResSignin(res, this);
                    break;

                default:
                    break;
            }

        }

    }
}
