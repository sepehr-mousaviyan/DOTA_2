package sbu.cs.mahkats.Client.UI.Controller;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
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

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    private static Stage stage;
    private Thread updater;
    Config config;

    public String recievedTeamName = "Error";

    private static char move;
    private static int cc;

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
    private ImageView ability1;

    @FXML
    private ImageView ability2;

    @FXML
    private ImageView ability3;


    public static void animationAttackMethod(ImageView pic) {
        RotateTransition rotate1 = new RotateTransition(Duration.millis(100), pic);
        rotate1.setFromAngle(-10);
        rotate1.setToAngle(10);
        rotate1.play();
        rotate1.setOnFinished((e) -> {
            try {
                RotateTransition rotate2 = new RotateTransition(Duration.millis(100), pic);
                rotate2.setFromAngle(10);
                rotate2.setToAngle(-10);
                rotate2.play();
                rotate2.setOnFinished((ex) -> {
                    try {
                        RotateTransition rotate3 = new RotateTransition(Duration.millis(100), pic);
                        rotate3.setFromAngle(-10);
                        rotate3.setToAngle(0);
                        rotate3.play();
                    } catch (Exception exp) {

                    }
                });
            } catch (Exception ex) {

            }
        });
    }

    @FXML
    public void checkUnits(ArrayList<HeroData> heroes, ArrayList<AbilityData> abilities, ArrayList<CreepData> creeps, ArrayList<BuildingData> buildings) {
        try {
            System.out.println("CheckList *****************");
            if (cc++ < 5) {
                print_data(heroes, abilities, creeps, buildings);
            }

            AnchorPane newMap = FXMLLoader.load(getClass().getResource("/MapBoard.fxml"));
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
                if (hero.getHeroType().equalsIgnoreCase("Ranger")) {
                    if (hero.isDie()) {
                        heroes.remove(hero);
                    } else {
                        heroName.setText("Drow Ranger");
//                        InputStream stream_hero = new FileInputStream("src/main/resources/Photos/rangerHero.png");
                        InputStream stream_hero = this.getClass().getResourceAsStream("/Photos/rangerHero.png");
                        InputStream stream_a1 = new FileInputStream("src/main/resources/Photos/Frost_Arrows_icon.png");
                        InputStream stream_a2 = new FileInputStream("src/main/resources/Photos/Multishot_icon.png");
                        InputStream stream_a3 = new FileInputStream("src/main/resources/Photos/Marksmanship_icon.png");
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

                        newMap.getChildren().add(imageView_hero);
                        imageView_hero.setPickOnBounds(true);
                        imageView_hero.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showHP.setText(Double.toString(hero.getHp()));
                                showMana.setText(Double.toString(hero.getMana()));
                            }
                        });

                        if (hero.getAttacking()) {
                            animationAttackMethod(imageView_hero);
                        }
                    }
                    config = HeroConfig.getInstance("Ranger");
                    for (AbilityData ability : abilities) {
                        if (ability.getName().equalsIgnoreCase(config.getStringValue("hero.ranger.ability1.name"))) {
                            ability1_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()) {
                                ability1.setOnMouseClicked(event -> Connection.sendUseAbility(ability, hero));
                            } else {
                                ability1.setOpacity(0.3);
                            }
                            if (ability.isCanUnlock()) {
                                if (ability.isUnlock()) {
                                    upgrade_new_ability1.setText("UPGRADE");
                                } else {
                                    upgrade_new_ability1.setText("UNLOCK");
                                }
                                upgrade_new_ability1.setOnMouseClicked(event -> {
                                    if (!ability.isUnlock() && ability.isAvailable()) {
                                        Connection.sendNewAbility(ability, hero);
                                    }
                                    if (ability.isUnlock() && ability.isAvailable()) {
                                        Connection.sendUpgradeAbility(ability, hero);
                                    }
                                });
                            }
                        }
                        if (ability.getName().equalsIgnoreCase(config.getStringValue("hero.ranger.ability2.name"))) {
                            ability2_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()) {
                                ability2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            } else {
                                ability2.setOpacity(0.3);
                            }
                            if (ability.isCanUnlock()) {
                                if (ability.isUnlock()) {
                                    upgrade_new_ability2.setText("UPGRADE");
                                } else {
                                    upgrade_new_ability2.setText("UNLOCK");
                                }
                                upgrade_new_ability2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (!ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if (ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                        if (ability.getName().equalsIgnoreCase(config.getStringValue("hero.ranger.ability3.name"))) {
                            ability3_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()) {
                                upgrade_new_ability3.setText("UPGRADE");
                            } else {
                                upgrade_new_ability3.setText("UNLOCK");
                            }
                            if (ability.isUnlock()) {
                                ability3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            } else {
                                ability3.setOpacity(0.3);
                            }
                            if (ability.isCanUnlock()) {
                                upgrade_new_ability3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (!ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if (ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }

                if (hero.getHeroType().equalsIgnoreCase("Knight")) {
                    if (hero.isDie()) {
                        heroes.remove(hero);
                    } else {
                        heroName.setText("Dragon Knight");
                        InputStream stream_hero = new FileInputStream("src/main/resources/Photos/knightHero.png");
                        InputStream stream_a1 = new FileInputStream("src/main/resources/Photos/Breathe_Fire_icon.png");
                        InputStream stream_a2 = new FileInputStream("src/main/resources/Photos/Dragon_Tail_icon.png");
                        InputStream stream_a3 = new FileInputStream("src/main/resources/Photos/Elder_Dragon_Form_icon.png");
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

                        newMap.getChildren().add(imageView_hero);
                        imageView_hero.setPickOnBounds(true);
                        imageView_hero.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showHP.setText(Double.toString(hero.getHp()));
                                showMana.setText(Double.toString(hero.getMana()));
                            }
                        });

                        if (hero.getAttacking()) {
                            animationAttackMethod(imageView_hero);
                        }
                    }
                    config = HeroConfig.getInstance("Knight");

                    for (AbilityData ability : abilities) {
                        if (ability.getName().equalsIgnoreCase(config.getStringValue("hero.knight.ability1.name"))) {
                            ability1_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()) {
                                ability1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            } else {
                                ability1.setOpacity(0.3);
                            }
                            if (ability.isCanUnlock()) {
                                if (ability.isUnlock()) {
                                    upgrade_new_ability1.setText("UPGRADE");
                                } else {
                                    upgrade_new_ability1.setText("UNLOCK");
                                }
                                upgrade_new_ability1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (!ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if (ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                        if (ability.getName().equalsIgnoreCase(config.getStringValue("hero.knight.ability2.name"))) {
                            ability2_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()) {
                                ability2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            } else {
                                ability2.setOpacity(0.3);
                            }
                            if (ability.isCanUnlock()) {
                                if (ability.isUnlock()) {
                                    upgrade_new_ability2.setText("UPGRADE");
                                } else {
                                    upgrade_new_ability2.setText("UNLOCK");
                                }
                                upgrade_new_ability2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (!ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if (ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendUpgradeAbility(ability, hero);
                                        }
                                    }
                                });
                            }
                        }
                        if (ability.getName().equalsIgnoreCase(config.getStringValue("hero.knight.ability3.name"))) {
                            ability3_stage.setText(Integer.toString(ability.getStage()));
                            if (ability.isUnlock()) {
                                upgrade_new_ability3.setText("UPGRADE");
                            } else {
                                upgrade_new_ability3.setText("UNLOCK");
                            }
                            if (ability.isUnlock()) {
                                ability3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Connection.sendUseAbility(ability, hero);
                                    }
                                });
                            } else {
                                ability3.setOpacity(0.3);
                            }
                            if (ability.isCanUnlock()) {
                                upgrade_new_ability3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (!ability.isUnlock() && ability.isAvailable()) {
                                            Connection.sendNewAbility(ability, hero);
                                        }
                                        if (ability.isUnlock() && ability.isAvailable()) {
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
                if (creep.getTeamName().equalsIgnoreCase("RED")) {
                    if (creep.getTypeCreep().equalsIgnoreCase("Ranged")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showHP.setText(Double.toString(creep.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    } else {

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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showHP.setText(Double.toString(creep.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                } else {
                    if (creep.getTypeCreep().equalsIgnoreCase("Ranged")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showHP.setText(Double.toString(creep.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (creep.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    } else {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showHP.setText(Double.toString(creep.getHp()));
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
                if (building.getTypeBuilding().equalsIgnoreCase("Tower")) {
                    if (building.getTeamName().equalsIgnoreCase("RED")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showHP.setText(Double.toString(building.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }

                        }
                    }
                    if (building.getTeamName().equalsIgnoreCase("Green")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(event -> {
                                showHP.setText(Double.toString(building.getHp()));
                                showMana.setText("");
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                }

                if (building.getTypeBuilding().equalsIgnoreCase("Ancient")) {
                    System.out.println("ancient dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    if (building.getTeamName().equalsIgnoreCase("RED")) {
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
                            System.out.println("is die amcient");
                            buildings.remove(building);
                            imageView.setImage(null);
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(event -> {
                                showHP.setText(Double.toString(building.getHp()));
                                showMana.setText("");
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    if (building.getTeamName().equalsIgnoreCase("GREEN")) {
                        InputStream stream = new FileInputStream("src/main/resources/Photos/Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(75);
                        imageView.setPickOnBounds(true);
                        System.out.println("is sddddddddddddfsgsdgsdfgdfg");
                        if (building.getAttacking()) {
                            animationAttackMethod(imageView);
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(event -> {
                                showHP.setText(Double.toString(building.getHp()));
                                showMana.setText("");
                            });
                            if (building.isDie()) {
                                buildings.remove(building);
                                imageView.setImage(null);
                            }
                        }
                    }

                }
                if (building.getTypeBuilding().equalsIgnoreCase("Ranged")) {
                    if (building.getTeamName().equalsIgnoreCase("RED")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showHP.setText(Double.toString(building.getHp()));
                                    showMana.setText("");
                                }
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    if (building.getTeamName().equalsIgnoreCase("GREEN")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(event -> {
                                showHP.setText(Double.toString(building.getHp()));
                                showMana.setText("");
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                }
                if (building.getTypeBuilding().equalsIgnoreCase("MELEE")) {
                    if (building.getTeamName().equalsIgnoreCase("RED")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(event -> {
                                showHP.setText(Double.toString(building.getHp()));
                                showMana.setText("");
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                    if (building.getTeamName().equalsIgnoreCase("GREEN")) {
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
                        } else {
                            newMap.getChildren().add(imageView);
                            imageView.setOnMouseEntered(event -> {
                                showHP.setText(Double.toString(building.getHp()));
                                showMana.setText("");
                            });
                            if (building.getAttacking()) {
                                animationAttackMethod(imageView);
                            }
                        }
                    }
                }
            }
            mainAnchor.getChildren().add(newMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void print_data(ArrayList<HeroData> heroes, ArrayList<AbilityData> abilities, ArrayList<CreepData> creeps, ArrayList<BuildingData> buildings) {
        System.out.println("heros");
        for (HeroData hero : heroes) {
            System.out.println(hero);
        }
        System.out.println("buildings");
        for (BuildingData building : buildings) {
            System.out.println(building);
        }
        System.out.println("###################################################################################");
    }

    public void setFinished(String winnerName) {
        try {
            if (winnerName.equalsIgnoreCase("GREEN"))
                setWinnerTeamName("Green");
            else
                setWinnerTeamName("Red");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EndGame.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeStage() {
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
        updater = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (ReceiveDataRunnable.isIsReloaded()) {
                        System.out.println("start check");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                checkUnits(ReceiveDataRunnable.getHeroes(), ReceiveDataRunnable.getAbilities(), ReceiveDataRunnable.getCreeps(), ReceiveDataRunnable.getBuildings());
                            }
                        });
                        ReceiveDataRunnable.setIsReloaded(false);
                        System.out.println("end check");
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        updater.start();
        System.out.println("thread updater started");
    }
}