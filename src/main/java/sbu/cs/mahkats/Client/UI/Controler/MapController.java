package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private AnchorPane mainAnchor;

    @FXML
    private Label teamNameOnMap;

    @FXML
    private Label showXP;

    @FXML
    private Label showArmor;

    @FXML
    private Label heroName;

    @FXML
    private ImageView ability1;

    @FXML
    private ImageView ability2;

    @FXML
    private ImageView ability3;

    @FXML
    private Label ability1_stage;

    @FXML
    private Label ability2_stage;

    @FXML
    private Label ability3_stage;



    public static void animationAttackMethod(ImageView pic){
        RotateTransition rotate1 = new RotateTransition(Duration.millis(100),pic);
        rotate1.setFromAngle(-10);
        rotate1.setToAngle(10);
        rotate1.play();
        rotate1.setOnFinished((e) -> {
            try {
                RotateTransition rotate2 = new RotateTransition(Duration.millis(100),pic);
                rotate2.setFromAngle(10);
                rotate2.setToAngle(-10);
                rotate2.play();
                rotate2.setOnFinished((ex) -> {
                    try {
                        RotateTransition rotate3 = new RotateTransition(Duration.millis(100),pic);
                        rotate3.setFromAngle(-10);
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
                if (hero.getTypeHero().equals("Ranged")){
                    InputStream stream_hero = new FileInputStream("/rangerHero.png");
                    InputStream stream_a1 = new FileInputStream("/rangerHero.png");
                    InputStream stream_a2 = new FileInputStream("/rangerHero.png");
                    InputStream stream_a3 = new FileInputStream("/rangerHero.png");
                    Image image_hero = new Image(stream_hero);
                    Image image_a1 = new Image(stream_hero);
                    Image image_a2 = new Image(stream_hero);
                    Image image_a3 = new Image(stream_hero);
                    ImageView imageView_hero = new ImageView();

                    imageView_hero.setImage(image_hero);
                    imageView_hero.setX(hero.getLocation_x());
                    imageView_hero.setY(hero.getLocation_y());
                    imageView_hero.setFitHeight(35);
                    imageView_hero.setFitWidth(35);

                    ability1.setImage(image_a1);
                    ability2.setImage(image_a2);
                    ability3.setImage(image_a3);

                    ability1_stage.setText(hero.getA);

                }
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
                        imageView.setPickOnBounds(true);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (creep.isDie()) {
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);
                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }

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
                        imageView.setPickOnBounds(true);

                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.isDie()) {
                                buildings.remove(building);
                                imageView.setImage(null);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
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
                        imageView.setPickOnBounds(true);

                        if (building.isDie()) {
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                        else {
                            mainAnchor.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showXP.setText();
                                    showArmor.setText();
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void BreathAction(MouseEvent event) {

    }

    @FXML
    void ElderFormAction(MouseEvent event) {

    }

    @FXML
    void TailAction(MouseEvent event) {

    }
    @FXML
    void FrostAction(MouseEvent event) {

    }

    @FXML
    void MarkmanshipAction(MouseEvent event) {

    }

    @FXML
    void MultiAction(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        teamNameOnMap.setText();
    }
}
