package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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

    private String creepImg = "";

    @FXML
    ImageView pic;

    @FXML
    private AnchorPane mainAnchor;

    public static ArrayList<ImageView> images = new ArrayList<>();

    @FXML
    void test(MouseEvent event) throws FileNotFoundException {
        System.out.println("************************");
        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ranged_Creep_Dire_model.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(30);
        imageView.setY(30);
        imageView.setPreserveRatio(true);
        mainAnchor.getChildren().add(imageView);
    }

    void setImages (ArrayList<ImageView> images){

    }

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

   public static void checkUnits(ArrayList<HeroData> heroes, ArrayList<CreepData> creeps, ArrayList<BuildingData> buildings) {
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
                //TODO: draw game
                if(creep.getTeamName().equals("RED")){
                    if(creep.getTypeCreep().equals("Ranged")) {
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ranged_Creep_Dire_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());

                        if (creep.isDie()) {
                            //TODO: delete unit in ui
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }

                        images.add(imageView);
                        //mainAnchor.getChildren().add(imageView);
                        if (creep.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }

                    }
                    else {

                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Melee_Creep_Dire_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());

                        if (creep.isDie()) {
                            //TODO: delete unit in ui
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        if (creep.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }

                    }
                }

                else {
                    if(creep.getTypeCreep().equals("Ranged")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ranged_Creep_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());

                        if (creep.isDie()) {
                            //TODO: delete unit in ui
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        if (creep.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                    }
                    else {
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Melee_Creep_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(creep.getLocation_x());
                        imageView.setY(creep.getLocation_y());

                        if (creep.isDie()) {
                            //TODO: delete unit in ui
                            creeps.remove(creep);
                            imageView.setImage(null);
                        }
                        if (creep.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                    }
                }
            }
            for (BuildingData building : buildings) {
                //TODO: draw game
                if (building.getTypeBuilding().equals("Tower")) {
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Tower_Dire_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                    if (building.getTeamName().equals("Green")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Tower_Radiant_model2.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                }

                if (building.getTypeBuilding().equals("Ancient")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Dire_model5.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }

                }

                if (building.getTypeBuilding().equals("Ancient")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }

                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }

                    }
                }

                if (building.getTypeBuilding().equals("Ranged")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                }
                if (building.getTypeBuilding().equals("MELEE")){
                    if (building.getTeamName().equals("RED")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                    if (building.getTeamName().equals("GREEN")){
                        InputStream stream = new FileInputStream("D:\\TERM 2\\AP\\APFinalProject\\DOTA_2\\src\\main\\resources\\Photos\\Ancient_Radiant_model.png");
                        Image image = new Image(stream);
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setX(building.getLocation_x());
                        imageView.setY(building.getLocation_y());

                        if (building.getAttacking()) {
                            //TODO: animation of attack
                            animationAttackMethod(imageView);
                        }
                        if (building.isDie()) {
                            //TODO: delete unit in ui
                            buildings.remove(building);
                            imageView.setImage(null);
                        }
                    }
                }

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
