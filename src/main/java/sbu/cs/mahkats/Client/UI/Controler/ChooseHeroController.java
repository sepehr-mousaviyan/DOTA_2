package sbu.cs.mahkats.Client.UI.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Client.Connection.Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private ImageView drowHero;

    @FXML
    private ImageView dragonHero;

    @FXML
    private Label rangerMaxDamage;

    @FXML
    private Label rangerMinDamage;

    @FXML
    private Label knightMaxDamage;

    @FXML
    private Label knightMinDamage;

    @FXML
    private Label choosen;

    @FXML
    private AnchorPane screen;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection.getHeroesData();
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
                dragonHero.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        choosenHeroName = "knight";
                        choosen.setText("You Choose");
                    }
                });
                /*try {
                    InputStream stream_hero = new FileInputStream("/Photos/dragon_knight.png");
                    Image image_hero = new Image(stream_hero);
                    dragonHero.setImage(image_hero);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
            }
            else if (hero.getHeroType().equals("Ranger")){
                rangerMaxDamage.setText(Double.toString(hero.getMaximum_damage()));
                rangerMinDamage.setText(Double.toString(hero.getMinimum_damage()));
                drowHero.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        choosenHeroName = "Ranger";
                        choosen.setText("You Choose");
                    }
                });
                /*try {
                    InputStream stream_hero = new FileInputStream("/Photos/drow_ranger.png");
                    Image image_hero = new Image(stream_hero);
                    drowHero.setImage(image_hero);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
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
    void goToSecondWaitingScreen(ActionEvent event) {
        try {
            Connection.sendSelectedHero(choosenHeroName);
            Parent logParent = null;
            logParent = FXMLLoader.load(getClass().getResource("/testMap.fxml"));
            Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene logScene = new Scene(logParent);

            logStage.setScene(logScene);
            logStage.show();
/*
            Stage myStage = new Stage();
            Scene scene;

            URL location = getClass().getResource("/testMap.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(location);
            scene = new Scene(loader.load(location.openStream()), 900, 650);
            myStage.setScene(scene);
            myStage.setTitle("Character Setting");
            myStage.show();*/
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
