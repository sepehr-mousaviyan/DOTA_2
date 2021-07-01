package sbu.cs.mahkats.Client.UI.Controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sbu.cs.mahkats.Client.Connection.Connection;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingOneController implements Initializable {

    private static boolean isReceived = false;
    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    public static boolean isIsReceived() {
        return isReceived;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("load one intilaiadas");
        Thread thread = new Thread(() ->{
            isReceived = Connection.getHeroesData();
        });
        thread.start();
        String videoOneAddress = "/video_2021-06-28_21-56-03.mp4";
        //Media media = new Media(videoOneAddress);
        //MediaPlayer mediaPlayer = new MediaPlayer(media);
        //loadingVideo.setMediaPlayer(mediaPlayer);
        //mediaPlayer.play();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isReceived) {
            try {
                //currentStage.close();
                //MainController.closeNext();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ChooseHeroScreen.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
