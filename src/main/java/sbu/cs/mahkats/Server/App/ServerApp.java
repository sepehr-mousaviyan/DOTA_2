package sbu.cs.mahkats.Server.App;

import sbu.cs.mahkats.Server.Connection.Client.Connection;

public class ServerApp {
    public static void main(String[] argv) throws InterruptedException {
        Connection server = new Connection();
        while(server.getHeroName().size() < 2){
            Thread.sleep(1500);
        }     
        new GamePlay(server.getHeroName().get(0) , server.getHeroName().get(1)).play(server.getClients());
    }
}
