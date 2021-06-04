package sbu.cs.mahkats.Server.Connection.Client;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Connection implements Runnable {

    private static final long ThreadID = 2;
    private ServerSocket serverSocket;
    private ArrayList<Client> clients;
    private final static Logger LOGGER = Logger.getLogger(Connection.class.getName());

    public Connection(ArrayList<Client> clients) {
        this.clients = clients;

        while (true)
        {
            try {
                Socket socket = serverSocket.accept();
                LOGGER.info("a Socket connected to: " + socket.getInetAddress());
            } catch (IOException e) {
                LOGGER.fatal("a Socket didn't connect", e);
                e.printStackTrace();
            }
            Thread thread = new Thread(this);
            thread.start();
        }
    }


    @Override
    public void run() {
        try {
            Socket socket  = serverSocket.accept();
            Client client = new Client(Thread.currentThread().getId(), socket);
            clients.add(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
