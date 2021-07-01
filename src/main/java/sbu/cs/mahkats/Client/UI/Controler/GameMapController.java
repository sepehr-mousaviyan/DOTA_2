package sbu.cs.mahkats.Client.UI.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sbu.cs.mahkats.Configuration.Config;

public class GameMapController {
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label abilityOneStage;

    @FXML
    private Label abilityTwoStage;

    @FXML
    private Label abilityThreeStage;

    @FXML
    private Label new_upgrade_abilityOne;

    @FXML
    private Label new_upgrade_abilityTwo;

    @FXML
    private Label new_upgrade_abilityThree;

    @FXML
    private Label teamNameOnMap;

    @FXML
    private Label heroName;

    @FXML
    private Label showMana;

    @FXML
    private Label showHP;

    Config config;

    private char move;

    public char getMove() {
        return move;
    }

    @FXML
    void downAction(ActionEvent event) {

    }

    @FXML
    void leftAction(ActionEvent event) {

    }

    @FXML
    void rightAction(ActionEvent event) {

    }

    @FXML
    void uoAction(ActionEvent event) {

    }


}
