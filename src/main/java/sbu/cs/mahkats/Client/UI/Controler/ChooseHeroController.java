package sbu.cs.mahkats.Client.UI.Controler;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Client.Connection.Connection;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseHeroController implements Initializable {

    private static ArrayList<HeroData> heroes;

    private static String choosenHeroName;
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

    public static void setHeroes(ArrayList<HeroData> hero) {
        heroes = hero;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection.getHeroesData();

        for (HeroData hero : heroes) {
            if (hero.getHeroType().equals("Knight")) {
                knightMaxDamage.setText(Double.toString(hero.getMaximum_damage()));
                knightMinDamage.setText(Double.toString(hero.getMinimum_damage()));
                dragonHero.setOnMouseClicked(mouseEvent -> {
                    choosenHeroName = "knight";
                });
            } else if (hero.getHeroType().equals("Ranger")) {
                rangerMaxDamage.setText(Double.toString(hero.getMaximum_damage()));
                rangerMinDamage.setText(Double.toString(hero.getMinimum_damage()));
                drowHero.setOnMouseClicked(mouseEvent -> {
                    choosenHeroName = "Ranger";
                });
            }
        }
    }


    @FXML
    void playHero1() {
        choosenHeroName = "Knight";
        choosen.setText("Your chosen Hero: Knight");
    }

    @FXML
    void playHero2() {
        choosenHeroName = "Ranger";
        choosen.setText("Your chosen Hero: Ranger");
    }


    @FXML
    void goToSecondWaitingScreen(ActionEvent event) {
        try {
            Connection.sendSelectedHero(choosenHeroName);
            Connection.getTeamName();
            Connection.runReceiver();

            //Platform.exit();

            Parent logParent = null;
            logParent = FXMLLoader.load(getClass().getResource("/Map.fxml"));
            Stage s1 = (Stage)((Node) event.getSource()).getScene().getWindow();

            Scene logScene = new Scene(logParent);

            s1.setScene(logScene);
            s1.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
