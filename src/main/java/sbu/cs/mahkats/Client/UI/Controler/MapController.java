package sbu.cs.mahkats.Client.UI.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private Label showHp;
    @FXML
    private ImageView pic;


    @FXML
    void show(MouseEvent event,ImageView pic ) {
        showHp.setText("hi");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
