package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sbu.cs.mahkats.Api.Data.BuildingData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    private String creepImg = "";

    @FXML
    ImageView pic;

    @FXML
    private ImageView greenAnceint;

    @FXML
    private ImageView redAncient;

    public static void animationAttackMethod(ImageView pic){
        RotateTransition rotate1 = new RotateTransition(Duration.millis(200),pic);
        rotate1.setFromAngle(-15);
        rotate1.setToAngle(15);
        rotate1.play();
        rotate1.setOnFinished((e) -> {
            try {
                RotateTransition rotate2 = new RotateTransition(Duration.millis(200),pic);
                rotate2.setFromAngle(15);
                rotate2.setToAngle(-15);
                rotate2.play();
                rotate2.setOnFinished((ex) -> {
                    try {
                        RotateTransition rotate3 = new RotateTransition(Duration.millis(200),pic);
                        rotate3.setFromAngle(-15);
                        rotate3.setToAngle(0);
                        rotate3.play();
                    }catch (Exception exp){

                    }
                });
            }catch (Exception ex){

            }
        });
    }

    static void checkUnits(ArrayList<HeroData> heroes, ArrayList<CreepData> creeps, ArrayList<BuildingData> buildings) {
        try {
            for (HeroData hero : heroes) {
                //TODO: draw game
                if (hero.getAttacking()) {
                    //TODO: animation of attack

                }
                if (hero.isDie()) {
                    //TODO: delete unit in ui
                    heroes.remove(hero);
                }

            }
            for (CreepData creep : creeps) {
                if (creep.getAttacking()) {
                    //TODO: animation of attack
                }
                if (creep.isDie()) {
                    //TODO: delete unit in ui
                    creeps.remove(creep);
                }
                //TODO: draw game
            }
            for (BuildingData building : buildings) {
                //TODO: draw game


                if (building.getAttacking()) {
                    //TODO: animation of attack
                }
                if (building.isDie()) {
                    //TODO: delete unit in ui
                    buildings.remove(building);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
