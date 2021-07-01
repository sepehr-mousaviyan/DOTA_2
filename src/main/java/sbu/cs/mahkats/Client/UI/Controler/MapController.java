package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sbu.cs.mahkats.Api.Data.AbilityData;
import sbu.cs.mahkats.Api.Data.BuildingData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Client.Connection.Connection;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.Units.HeroConfig;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    private static Stage stage;

    Config config;

    public String recievedTeamName = "Error";

    private static char move;

    private static String winnerTeamName;

    public void setWinnerTeamName(String winner) {
        winnerTeamName = winner;
    }

    public static String getWinnerName() {
        return winnerTeamName;
    }

    public static char getMove() {
        return move;
    }

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label teamNameOnMap;

    @FXML
    private Label heroName;

    @FXML
    private Label showHP;

    @FXML
    private Label showMana;

    @FXML
    private Label ability1_stage;

    @FXML
    private Label ability2_stage;

    @FXML
    private Label ability3_stage;

    @FXML
    private Label upgrade_new_ability1;

    @FXML
    private Label upgrade_new_ability2;

    @FXML
    private Label upgrade_new_ability3;


    @FXML
    private Label ability1_stage1;

    @FXML
    private Label ability1_stage2;

    @FXML
    private Label ability1_stage3;


    @FXML
    private ImageView rAbility1;

    @FXML
    private ImageView rAbility2;

    @FXML
    private ImageView rAbility3;

    @FXML
    private ImageView dAbility1;

    @FXML
    private ImageView dAbility2;

    @FXML
    private ImageView dAbility3;




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

    @FXML
    public void checkUnits(ArrayList<HeroData> heroes, ArrayList<AbilityData> abilities, ArrayList<CreepData> creeps, ArrayList<BuildingData> buildings) {
        try {
            System.out.println("CheckList *****************");
           /*heroName = new Label();
           ability1 = new ImageView();
           ability2 = new ImageView();
           ability3 = new ImageView();
           mainAnchor = new AnchorPane();
           teamNameOnMap = new Label();
           showHP = new Label();
           showMana=new Label();
           ability1_stage = new Label();
           ability2_stage = new Label();
           ability3_stage = new Label();
           upgrade_new_ability1 = new Label();
           upgrade_new_ability2 = new Label();
           upgrade_new_ability3 = new Label();*/
            for (HeroData hero : heroes) {
                if (hero.getHeroType().equals("Ranger")){
                    if (hero.isDie()) {
                        heroes.remove(hero);
                    }
                    else {
                        heroName.setText("Drow Ranger");
                        InputStream stream_hero = new FileInputStream("src/main/resources/Photos/rangerHero.png");
                        Image image_hero = new Image(stream_hero);
                        ImageView imageView_hero = new ImageView();

                        imageView_hero.setImage(image_hero);
                        imageView_hero.setX(hero.getLocation_x());
                        System.out.println("********************\n"+hero.getLocation_x());
                        imageView_hero.setY(hero.getLocation_y());
                        System.out.println("********************\n"+hero.getLocation_y());

                        imageView_hero.setFitHeight(25);
                        imageView_hero.setFitWidth(25);

                        mainAnchor.getChildren().add(imageView_hero);
                        imageView_hero.setPickOnBounds(true);
                        imageView_hero.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showHP.setText(Double. toString(hero.getHp()));
                                showMana.setText(Double. toString(hero.getMana()));
                            }
                        });

                        if (hero.getAttacking()) {
                            animationAttackMethod(imageView_hero);
                        }
                    }
                    config = HeroConfig.getInstance("Ranger");
                    for (AbilityData ability : abilities){
                        if(ability.getName().equals(config.getStringValue("hero.ranger.ability1.name"))){
                            ability1_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()){
                                rAbility1.setOnMouseClicked(event -> Connection.sendUseAbility(ability, hero));
                            }
                            else {
                                rAbility1.setOpacity(0.3);
                            }
                            if(ability.isCanUnlock()) {
                                if (ability.isUnlock()){
                                    upgrade_new_ability1.setText("UPGRADE");
                                }
                                else {
                                    upgrade_new_ability1.setText("UNLOCK");
                                }
                                upgrade_new_ability1.setOnMouseClicked(event -> {
                                    if(!ability.isUnlock() && ability.isAvailable()){
                                        Connection.sendNewAbility(ability, hero);
                                    }
                                    if(ability.isUnlock() && ability.isAvailable()){
                                        Connection.sendUpgradeAbility(ability, hero);
                                    }
                                });
                            }
                        }
                        if(ability.getName().equals(config.getStringValue("hero.ranger.ability2.name"))){
                            ability2_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()){
                                rAbility2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            }
                            else {
                                rAbility2.setOpacity(0.3);
                            }
                            if(ability.isCanUnlock()) {
                                if (ability.isUnlock()){
                                    upgrade_new_ability2.setText("UPGRADE");
                                }
                                else {
                                    upgrade_new_ability2.setText("UNLOCK");
                                }
                                upgrade_new_ability2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(!ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if(ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                        if(ability.getName().equals(config.getStringValue("hero.ranger.ability3.name"))){
                            ability3_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()){
                                upgrade_new_ability3.setText("UPGRADE");
                            }
                            else {
                                upgrade_new_ability3.setText("UNLOCK");
                            }
                            if (ability.isUnlock()){
                                rAbility3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            }
                            else {
                                rAbility3.setOpacity(0.3);
                            }
                            if(ability.isCanUnlock()) {
                                upgrade_new_ability3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(!ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if(ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }

                if (hero.getHeroType().equals("Knight")){
                    if (hero.isDie()) {
                        heroes.remove(hero);
                    }
                    else {
                        heroName.setText("Dragon Knight");
                        InputStream stream_hero = new FileInputStream("src/main/resources/Photos/knightHero.png");
                        Image image_hero = new Image(stream_hero);
                        ImageView imageView_hero = new ImageView();

                        imageView_hero.setImage(image_hero);
                        imageView_hero.setX(hero.getLocation_x());
                        imageView_hero.setY(hero.getLocation_y());
                        imageView_hero.setFitHeight(25);
                        imageView_hero.setFitWidth(25);

                        mainAnchor.getChildren().add(imageView_hero);
                        imageView_hero.setPickOnBounds(true);
                        imageView_hero.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showHP.setText(Double. toString(hero.getHp()));
                                showMana.setText(Double. toString(hero.getMana()));
                            }
                        });

                        if (hero.getAttacking()) {
                            animationAttackMethod(imageView_hero);
                        }
                    }
                    config = HeroConfig.getInstance("Knight");

                    for (AbilityData ability : abilities){
                        if(ability.getName().equals(config.getStringValue("hero.knight.ability1.name"))){
                            ability1_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()){
                                dAbility1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            }
                            else {
                                dAbility1.setOpacity(0.3);
                            }
                            if(ability.isCanUnlock()) {
                                if (ability.isUnlock()){
                                    upgrade_new_ability1.setText("UPGRADE");
                                }
                                else {
                                    upgrade_new_ability1.setText("UNLOCK");
                                }
                                upgrade_new_ability1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(!ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if(ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                        if(ability.getName().equals(config.getStringValue("hero.knight.ability2.name"))){
                            ability2_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()){
                                dAbility2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            }
                            else {
                                dAbility2.setOpacity(0.3);
                            }
                            if(ability.isCanUnlock()) {
                                if (ability.isUnlock()){
                                    upgrade_new_ability2.setText("UPGRADE");
                                }
                                else {
                                    upgrade_new_ability2.setText("UNLOCK");
                                }
                                upgrade_new_ability2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(!ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if(ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                        if(ability.getName().equals(config.getStringValue("hero.knight.ability3.name"))){
                            ability3_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()){
                                upgrade_new_ability3.setText("UPGRADE");
                            }
                            else {
                                upgrade_new_ability3.setText("UNLOCK");
                            }
                            if (ability.isUnlock()){
                                dAbility3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            }
                            else {
                                dAbility3.setOpacity(0.3);
                            }
                            if(ability.isCanUnlock()) {
                                upgrade_new_ability3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(!ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if(ability.isUnlock() && ability.isAvailable()){
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }

            for (CreepData creep : creeps) {
                if(creep.getTeamName().equals("RED")){
                    if(creep.getTypeCreep().equals("Ranged")) {
                        InputStream stream = new FileInputStream("src/main/resources/Photos/dire_ranged_creep.png");
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
                                    showHP.setText(Double. toString(creep.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    else {

                        InputStream stream = new FileInputStream("src/main/resources/Photos/dire_melee_creep.png");
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
                                    showHP.setText(Double. toString(creep.getHp()));
                                    showMana.setText("");
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
                        InputStream stream = new FileInputStream("src/main/resources/Photos/radiant_ranged_creep.png");
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
                                    showHP.setText(Double. toString(creep.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    else {
                        InputStream stream = new FileInputStream("src/main/resources/Photos/radiant_melee_creep.png");
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
                                    showHP.setText(Double. toString(creep.getHp()));
                                    showMana.setText("");
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
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Tower_Dire_model.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }

                        }
                    }
                    if (building.getTeamName().equals("Green")){
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Tower_Radiant_model2.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
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
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Dire_model5.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Radiant_model.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
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
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Radiant_model.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Radiant_model.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
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
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Radiant_model.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Radiant_model.png");
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
                                    showHP.setText(Double. toString(building.getHp()));
                                    showMana.setText("");
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

    public void setFinished(String winnerName){
       try {
            if (winnerName.equals("GREEN"))
                setWinnerTeamName("Green");
            else
                setWinnerTeamName("Red");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EndGame.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception  e) {
           e.printStackTrace();
       }
    }

    public static void closeStage(){
        stage.close();
    }

    @FXML
    void downFunction(ActionEvent event) {
        move = 's';
    }

    @FXML
    void leftAction(ActionEvent event) {
        move = 'a';
    }

    @FXML
    void rightFunctin(ActionEvent event) {
        move = 'd';
    }

    @FXML
    void upFunction(ActionEvent event) {
        move = 'w';
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*AtomicBoolean isReceived = new AtomicBoolean(false);
        Thread thread = new Thread(() ->{
            isReceived.set(Connection.getTeamName());
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recievedTeamName = ReceiveDataRunnable.getTeamName();
        teamNameOnMap.setText(recievedTeamName);*/
        new Thread(()->{
            while(true) {
                if(!ReceiveDataRunnable.isIsReloaded()) {
                    checkUnits(ReceiveDataRunnable.getHeroes(), ReceiveDataRunnable.getAbilities(), ReceiveDataRunnable.getCreeps(), ReceiveDataRunnable.getBuildings());
                    ReceiveDataRunnable.setIsReloaded(false);
                }
            }
        }).start();
    }
}
