package sbu.cs.mahkats.Server.Connection.Client;

import org.apache.log4j.Logger;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Server.App.GamePlay;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Connection implements Runnable {

    private static final long NUMBER_PLAYER = 2;
    private ServerSocket serverSocket;
    private Socket socket;
    private ArrayList<Client> clients;
    private ArrayList<String> heroName;
    private static boolean isRed = false; 

    private final static Logger LOGGER = Logger.getLogger(Connection.class.getName());
    private final static ReentrantLock lock = new ReentrantLock();

    public Connection() {
        this.clients = new ArrayList<>();
        Config config = Config.getInstance();
        try {
            serverSocket = new ServerSocket(config.getIntValue("connection.port"));
            LOGGER.info("Server is started");
        } catch (IOException e) {
            LOGGER.fatal("server can not start", e);
        }

        int countPlayer = 0;
        while (countPlayer < NUMBER_PLAYER)
        {
            try {
                socket = serverSocket.accept();
                LOGGER.info("a Socket connected to: " + socket.getInetAddress());
            } catch (IOException e) {
                LOGGER.fatal("a Socket didn't connect", e);
            }
            Thread thread = new Thread(this);
            thread.start();
            LOGGER.info("player "+ (countPlayer+1) +" is connected!");
            countPlayer++;
        }
    }

    public ArrayList<String> getHeroName(){ return heroName;}

    public ArrayList<Client> getClients() {
        return clients;
    }

    synchronized private static boolean setRedTrue(){ 
        if(!isRed){ 
            isRed = true;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        Client client = new Client(Thread.currentThread().getId(), socket);
        clients.add(client);
        client.handlerLoginSignup();
        client.sendListHero();
        heroName = new ArrayList<String>();
        /*heroName.add(client.getSelectHero());
        if(setRedTrue()){
            client.send(new MassageMaker().massage("GREEN" , heroName.get(0)).toString());
            client.setTeamName("GREEN");
        }
        else{
            client.setTeamName("RED");
            if(heroName.get(0).equals("Knigth")){
                client.send(new MassageMaker().massage("RED" , "Knight").toString());
            } else {
                client.send(new MassageMaker().massage("RED" , "Ranger").toString());
            }
        }*/
        heroName.add("Knight");
        client.send(new MassageMaker().massage("GREEN" , "Knight").toString());
        heroName.add("Ranger");
        client.send(new MassageMaker().massage("GREEN" , "Ranger").toString());
        new GamePlay(heroName.get(0), heroName.get(1)).play(clients);
    }
}
