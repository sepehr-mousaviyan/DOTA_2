package sbu.cs.mahkats.Client.App;

import sbu.cs.mahkats.Client.Connection.Connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger logger =  Logger.getLogger(App.class.getName());
        Connection client = new Connection();
        if(client.getStatusConnection()) {
            App.main(args);
        }
        else{
            logger.log(Level.FINER,"connection to server failed in App class in main method");
        }
    }
}
