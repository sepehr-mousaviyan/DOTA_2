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

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingTwoController implements Initializable {
    @FXML
    private MediaView loadingVideo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String videoOneAddress = "/video_2021-06-28_21-56-03.mp4";
        Media media = new Media(videoOneAddress);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        loadingVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        if (true) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Map.fxml"));
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
