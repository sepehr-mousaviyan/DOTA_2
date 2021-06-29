package sbu.cs.mahkats.Client.UI.Controler;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseHeroController implements Initializable {

    @FXML
    private MediaView heroOne;

    @FXML
    private MediaView heroTwo;

    MediaPlayer mediaPlayerOne;
    MediaPlayer mediaPlayerTwo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String videoOneAddress = "/video_2021-06-28_18-43-17.mp4";
        String videoTwoAddress = "/video_2021-06-28_18-43-30.mp4";

        Media mediaOne = new Media(videoOneAddress);
        Media mediaTwo = new Media(videoTwoAddress);
        mediaPlayerOne = new MediaPlayer(mediaOne);
        mediaPlayerTwo = new MediaPlayer(mediaTwo);

        heroOne.setMediaPlayer(mediaPlayerOne);
        heroTwo.setMediaPlayer(mediaPlayerTwo);
    }


    @FXML
    void playHero1(MouseEvent event) {
        mediaPlayerOne.play();
    }

    @FXML
    void playHero2(MouseEvent event) {
        mediaPlayerTwo.play();
    }

    @FXML
    void goToSecondWaitingScreen(MouseEvent event) {
        try {

            //for now go to map
            Parent logParent = FXMLLoader.load(getClass().getResource("/LoadingScreenTwo.fxml"));
            Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene logScene = new Scene(logParent);

            logStage.setScene(logScene);
            logStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
