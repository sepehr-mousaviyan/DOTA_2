package sbu.cs.mahkats.Client.UI.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {

    @FXML
    private Label winnerTeam;


    /*@FXML
    void exitGame(MouseEvent event) {
        MapController.closeStage();
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        winnerTeam.setText(MapController.getWinnerName());
    }
}
