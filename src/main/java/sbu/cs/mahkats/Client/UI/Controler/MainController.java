package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane mainanchor;

    @FXML
    private TextField passInput;

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField emailInput;

    @FXML
    private Label invalidRespond;

    @FXML
    private Label emptyRespond;

    public void loadSplash(){
        try {
            SplashController.isPlayed = true;
            AnchorPane anch1 = FXMLLoader.load(getClass().getResource("/SplashScreen.fxml"));

            mainanchor.getChildren().setAll(anch1);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5),anch1);

            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5),anch1);

            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });
            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane anch2 = FXMLLoader.load(getClass().getResource("/Main.fxml"));
                    mainanchor.getChildren().setAll(anch2);
                }catch (Exception ex){

                }
            });
        }catch (Exception e){

        }
    }

    @FXML
    void signUpAction(ActionEvent event) {
        if (passInput.getText().isEmpty() || userNameInput.getText().isEmpty() || emailInput.getText().isEmpty())
            emptyRespond.setText("PLEASE ENTER ALL FEILDS!");
        if (!goodInput(userNameInput.getText(),passInput.getText(),emailInput.getText())){
            emptyRespond.setText("");
            invalidRespond.setText("DON'T USE ! , / , ? , ) , ( , * , & , % IN YOUR INPUT!");

        }
        //***********************************************************
//        else
//

    }

    private boolean goodInput(String userName, String password, String Email){
        boolean b1, b2, b3;
        b1 = checkString(userName);
        b2 = checkString(password);
        b3 = checkString(Email);

        return b1 && b2 && b3;
    }

    private boolean checkString(String str){
        boolean b = true;
        char[] charArray = {'%','&','*','(',')','/',':',';','!','?','$'};

        for (int i = 0; i < charArray.length; i++)
            if (str.indexOf(charArray[i]) > -1) {
                b = false;
                break;
            }
        return b;
    }

    @FXML
    void loginAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!SplashController.isPlayed)
            loadSplash();
    }
}
