package sbu.cs.mahkats.Client.UI.Controler;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sbu.cs.mahkats.Client.Connection.Connection;
import sbu.cs.mahkats.Configuration.Config;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Config config = Config.getInstance();
    private static String USERNAME_LENGTH = "input.limit.userName";
    private static String PASSWORD_LENGTH = "input.limit.passWord";
    private static String EMAIL_LENGTH = "input.limit.email";

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

    @FXML
    private Label serverRespond;

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
        if (passInput.getText().isEmpty() || userNameInput.getText().isEmpty() || emailInput.getText().isEmpty()){
            invalidRespond.setText("");
            emptyRespond.setText("PLEASE ENTER ALL FEILDS!");
        }
        if (!goodInput(userNameInput.getText(),passInput.getText(),emailInput.getText())){
            emptyRespond.setText("");
            invalidRespond.setText("DON'T USE ! , / , ? , ) , ( , * , & , % IN YOUR INPUT!");

        }

        else if(Connection.checkUserSignUp(userNameInput.getText(),passInput.getText(),emailInput.getText())){
            if(Connection.receive().equals("ok")){
                //**********************************
                //وارد منیو می شود
            }


            else {
                //********************************
                //اروری که در ریسیو سرور فرستاده را در لیبل چاپ می کند
                emptyRespond.setText("");
                invalidRespond.setText("");
                serverRespond.setText("shrhwwrtbw5jetjetkv");

            }
        }

        else {
            emptyRespond.setText("");
            invalidRespond.setText("");
            serverRespond.setText("connecting to server failed");
        }

    }

    private boolean goodInput(String userName, String password, String Email){
        boolean userName_check, password_check, email_check;
        userName_check = checkString(userName);
        password_check = checkString(password);
        email_check = checkString(Email);

        return userName_check && password_check && email_check;
    }

    private boolean checkString(String str){
        boolean str_check = true;
        char[] charArray = {'%','&','*','(',')','/',':',';','!','?','$'};

        for (int i = 0; i < charArray.length; i++)
            if (str.indexOf(charArray[i]) > -1) {
                str_check = false;
                break;
            }
        return str_check;
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        Parent logParent = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene logScene = new Scene(logParent);

        logStage.setScene(logScene);
        logStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!SplashController.isPlayed)
            loadSplash();
    }
}
