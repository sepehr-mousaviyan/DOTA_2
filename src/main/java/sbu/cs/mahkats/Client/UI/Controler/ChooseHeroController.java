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
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseHeroController implements Initializable {

    private static ArrayList<HeroData> heroes;

    private static String choosenHeroName;

    public static String getChoosenHeroName() {
        return choosenHeroName;
    }

    public static void setChoosenHeroName(String HeroName) {
        choosenHeroName = HeroName;
    }

    public static void setHeroes(ArrayList<HeroData> hero) {
        heroes = hero;
    }

    @FXML
    private Label heroName;

    @FXML
    private Label abilityOne;

    @FXML
    private Label abilityTwo;

    @FXML
    private Label abilityThree;


    @FXML
    private MediaView heroOne;

    @FXML
    private MediaView heroTwo;

    MediaPlayer mediaPlayerOne;
    MediaPlayer mediaPlayerTwo;

    @FXML
    private Button BreathFire;

    @FXML
    private Button tailAction;

    @FXML
    private Button elderAction;

    @FXML
    private Button frostAction;

    @FXML
    private Button breathAction;

    @FXML
    private Button markcmanshipAction;

    @FXML
    void BreathAction(MouseEvent event) {

    }


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
        for (HeroData hero : heroes){
            if (hero.getTypeHero().equals("Knight")){
                heroName.setText("Dragon " + hero.getTypeHero());
                abilityOne.setText("Breath Fire");
                abilityTwo.setText("Dragon Tail");
                abilityThree.setText("Elder Dragon Form");
                setChoosenHeroName("Knight");
            }
        }
    }

    @FXML
    void playHero2(MouseEvent event) {
        mediaPlayerTwo.play();
        for (HeroData hero : heroes){
            if (hero.getTypeHero().equals("Ranged")){
                heroName.setText("Drow" + hero.getTypeHero());
                abilityOne.setText("Frost Arrows");
                abilityTwo.setText("Multi Arrow");
                abilityThree.setText("Marksmanship");
                setChoosenHeroName("Ranged");
            }
        }
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
