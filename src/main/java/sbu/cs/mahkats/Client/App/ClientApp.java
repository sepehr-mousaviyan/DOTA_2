package sbu.cs.mahkats.Client.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.InterfaceConfig;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config config = InterfaceConfig.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));


        primaryStage.setTitle(config.getStringValue("stage.title"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
