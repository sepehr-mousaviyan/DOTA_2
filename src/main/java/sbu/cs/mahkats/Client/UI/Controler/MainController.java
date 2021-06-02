package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane mainanchor;

    public void loadSplash(){
        try {
            SplashScreen.isPlayed = true;
            AnchorPane anch1 = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
            mainanchor.getChildren().setAll(anch1);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5),anch1);

            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5),anch1);

            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });
            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane anch2 = FXMLLoader.load(getClass().getResource("Main.fxml"));
                    mainanchor.getChildren().setAll(anch2);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!SplashScreen.isPlayed)
            loadSplash();
    }
}
