package sbu.cs.mahkats.Client.UI.Controler;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sbu.cs.mahkats.Api.Data.AbilityData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Client.Connection.Connection;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseHeroController implements Initializable {

    private static ArrayList<HeroData> heroes;

    private static String choosenHeroName;


    public static void setHeroes(ArrayList<HeroData> hero) {
        heroes = hero;
    }

    @FXML
    private MediaView heroOne;

    @FXML
    private MediaView heroTwo;

    @FXML
    private Label rangerMaxDamage;

    @FXML
    private Label rangerMinDamage;

    @FXML
    private Label knightMaxDamage;

    @FXML
    private Label knightMinDamage;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String videoOneAddress = "/video_2021-06-28_18-43-17.mp4";
        String videoTwoAddress = "/video_2021-06-28_18-43-30.mp4";

        //Media mediaOne = new Media(videoOneAddress);
        //Media mediaTwo = new Media(videoTwoAddress);
        //mediaPlayerOne = new MediaPlayer(mediaOne);
        //mediaPlayerTwo = new MediaPlayer(mediaTwo);

        //heroOne.setMediaPlayer(mediaPlayerOne);
        //heroTwo.setMediaPlayer(mediaPlayerTwo);

        for (HeroData hero : heroes){
            if (hero.getHeroType().equals("Knight")){
                knightMaxDamage.setText(Double.toString(hero.getMaximum_damage()));
                knightMinDamage.setText(Double.toString(hero.getMinimum_damage()));
            }
            else if (hero.getHeroType().equals("Ranger")){
                rangerMaxDamage.setText(Double.toString(hero.getMaximum_damage()));
                rangerMinDamage.setText(Double.toString(hero.getMinimum_damage()));
            }
            //mediaPlayerOne.play();
            //mediaPlayerTwo.play();

        }
    }


    @FXML
    void playHero1(MouseEvent event) {
        choosenHeroName = "Knight";
    }

    @FXML
    void playHero2(MouseEvent event) {
        choosenHeroName = "Ranger";
    }

    @FXML
    void goToSecondWaitingScreen(MouseEvent event) {
        try {

            Connection.sendSelectedHero(choosenHeroName);
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
