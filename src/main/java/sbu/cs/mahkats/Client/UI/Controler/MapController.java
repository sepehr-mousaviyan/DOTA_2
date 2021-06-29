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
import sbu.cs.mahkats.Api.Data.BuildingData;
import sbu.cs.mahkats.Api.Data.CreepData;
import sbu.cs.mahkats.Api.Data.HeroData;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    private char move;

    private static String winnerTeamName;

    public void setWinnerTeamName(String winner) {
        winnerTeamName = winner;
    }

    public String getWinnerName() {
        return winnerTeamName;
    }

    public char getMove() {
        return move;
    }

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label teamNameOnMap;

    @FXML
    private Label showHP;

    @FXML
    private Label showMana;

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
                if (hero.getHeroType().equals("Ranged")){
                    if (hero.isDie()) {
                        heroes.remove(hero);
                    }
                    else {
                        heroName.setText("Drow Ranger");
                        InputStream stream_hero = new FileInputStream("/rangerHero.png");
                        InputStream stream_a1 = new FileInputStream("/Frost_Arrows_icon.png");
                        InputStream stream_a2 = new FileInputStream("/Multishot_icon.png");
                        InputStream stream_a3 = new FileInputStream("/Marksmanship_icon.png");
                        Image image_hero = new Image(stream_hero);
                        Image image_a1 = new Image(stream_a1);
                        Image image_a2 = new Image(stream_a2);
                        Image image_a3 = new Image(stream_a3);
                        ImageView imageView_hero = new ImageView();

                        imageView_hero.setImage(image_hero);
                        imageView_hero.setX(hero.getLocation_x());
                        imageView_hero.setY(hero.getLocation_y());
                        imageView_hero.setFitHeight(25);
                        imageView_hero.setFitWidth(25);

                        ability1.setImage(image_a1);
                        ability2.setImage(image_a2);
                        ability3.setImage(image_a3);

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
                }

                if (hero.getHeroType().equals("Knight")){
                    if (hero.isDie()) {
                        heroes.remove(hero);
                    }
                    else {
                        heroName.setText("Dragon Knight");
                        InputStream stream_hero = new FileInputStream("/knightHero.png");
                        InputStream stream_a1 = new FileInputStream("/Breathe_Fire_icon.png");
                        InputStream stream_a2 = new FileInputStream("/Dragon_Tail_icon.png");
                        InputStream stream_a3 = new FileInputStream("/Elder_Dragon_Form_icon.png");
                        Image image_hero = new Image(stream_hero);
                        Image image_a1 = new Image(stream_a1);
                        Image image_a2 = new Image(stream_a2);
                        Image image_a3 = new Image(stream_a3);
                        ImageView imageView_hero = new ImageView();

                        imageView_hero.setImage(image_hero);
                        imageView_hero.setX(hero.getLocation_x());
                        imageView_hero.setY(hero.getLocation_y());
                        imageView_hero.setFitHeight(25);
                        imageView_hero.setFitWidth(25);

                        ability1.setImage(image_a1);
                        ability2.setImage(image_a2);
                        ability3.setImage(image_a3);

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
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception  e){
            e.printStackTrace();
        }
    }

    @FXML
    void downFunction(ActionEvent event) {
        move = 'd';
    }

    @FXML
    void leftAction(ActionEvent event) {
        move = 'l';
    }

    @FXML
    void rightFunctin(ActionEvent event) {
        move = 'r';
    }

    @FXML
    void upFunction(ActionEvent event) {
        move = 'u';
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        teamNameOnMap.setText(getWinnerName());
    }
}
