package sbu.cs.mahkats.Client.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sbu.cs.mahkats.Client.Connection.Connection;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends Application {

    static Logger logger =  Logger.getLogger(App.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config config = InterfaceConfig.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));

        primaryStage.setTitle(config.getStringValue("title"));
        Scene scene = new Scene(root ,config.getIntValue("height") ,config.getIntValue("width"));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Connection client = new Connection();
        if(client.getStatusConnection()) {
            launch(args);
        }
        else{
            logger.log(Level.FINER,"connection to server failed in App class in main method");
        }
    }


}
