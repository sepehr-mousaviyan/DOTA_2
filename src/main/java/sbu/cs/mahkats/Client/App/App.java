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

public class App extends Application {

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
            System.out.println("Faild Connection!");
        }
    }


}
