package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sbu.cs.mahkats.Api.Data.BuildingData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    ImageView pic;

    @FXML
    private AnchorPane mainAnchor;


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


   public void checkUnits(ArrayList<HeroData> heroes, ArrayList<CreepData> creeps, ArrayList<BuildingData> buildings) {
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
                if(creep.getTeamName().equals("RED")){
                    if(creep.getTypeCreep().equals("Ranged")) {
                        InputStream stream = new FileInputStream("/dire_ranged_creep.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());
                        imageView.setFitHeight(25);
                        imageView.setFitWidth(25);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (creep.getAttacking()) {
                            animationAttackMethod(imageView);
                        }

                    }
                    else {

                        InputStream stream = new FileInputStream("/dire_melee_creep.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());
                        imageView.setFitHeight(25);
                        imageView.setFitWidth(25);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (creep.getAttacking()) {
                            animationAttackMethod(imageView);
                        }

                    }
                }

                else {
                    if(creep.getTypeCreep().equals("Ranged")){
                        InputStream stream = new FileInputStream("/radiant_ranged_creep.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());
                        imageView.setFitHeight(25);
                        imageView.setFitWidth(25);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (creep.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }
                    else {
                        InputStream stream = new FileInputStream("/radiant_melee_creep.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());
                        imageView.setFitHeight(25);
                        imageView.setFitWidth(25);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (creep.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }
                }
            }
            for (BuildingData building : buildings) {
                if (building.getTypeBuilding().equals("Tower")) {
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("/Tower_Dire_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                    if (building.getTeamName().equals("Green")){
                        InputStream stream = new FileInputStream("/Tower_Radiant_model2.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                }

                if (building.getTypeBuilding().equals("Ancient")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("/Ancient_Dire_model5.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(80);

                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("/Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(75);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }

                }
                if (building.getTypeBuilding().equals("Ranged")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("/Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(41);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("/Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(41);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }
                }
                if (building.getTypeBuilding().equals("MELEE")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("/Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(41);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(41);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        mainAnchor.getChildren().add(imageView);
                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                    }
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
