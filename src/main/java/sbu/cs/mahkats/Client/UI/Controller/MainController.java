package sbu.cs.mahkats.Client.UI.Controller;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController implements Initializable {

    private static Config config = Config.getInstance();
    private static final int USERNAME_LENGTH = config.getIntValue("input.limit.userName");
    private static final int PASSWORD_LENGTH = config.getIntValue("input.limit.passWord");
    private static final int EMAIL_LENGTH = config.getIntValue("input.limit.email");
    private static final String regexEmail = "^\\S+@\\S+\\.\\S+$";

    private static Stage logStage;

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

    @FXML
    private Label invalidEmail;


    public void loadSplash(){
        try {
            SplashController.isPlayed = true;
            AnchorPane anch1 = FXMLLoader.load(getClass().getResource("/SplashScreen.fxml"));

            mainanchor.getChildren().setAll(anch1);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5),anch1);

            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3),anch1);

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

    public static boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void closeNext(){
        logStage.close();
    }

    private boolean goodInput(String userName, String password, String email){
        boolean userName_check, password_check, email_check;
        userName_check = checkString(userName);
        password_check = checkString(password);
        email_check = checkString(email);

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

    public static boolean checkLength(String userName, String passWord, String email){
        return (userName.length() <= USERNAME_LENGTH) &&
                (passWord.length() <= PASSWORD_LENGTH) &&
                (email.length() <= EMAIL_LENGTH);

    }

    @FXML
    void signUpAction(ActionEvent event) {
        if (passInput.getText().isEmpty() || userNameInput.getText().isEmpty() || emailInput.getText().isEmpty()){
            invalidRespond.setText("");
            serverRespond.setText("");
            invalidEmail.setText("");
            emptyRespond.setText("PLEASE ENTER ALL FEILDS!");
        }
        else if (!goodInput(userNameInput.getText(),passInput.getText(),emailInput.getText())){
            emptyRespond.setText("");
            serverRespond.setText("");
            invalidEmail.setText("");
            invalidRespond.setText("DON'T USE ! , / , ? , ) , ( , * , & , % IN YOUR INPUT!");

        }
        else if (!isValidEmailAddress(emailInput.getText())){
            emptyRespond.setText("");
            serverRespond.setText("");
            invalidRespond.setText("");
            invalidEmail.setText("YOUR EMAILADDRESS IS INVALID!");
        }
        else if (!checkLength(userNameInput.getText(),passInput.getText(),emailInput.getText())){
            emptyRespond.setText("");
            serverRespond.setText("");
            invalidEmail.setText("");
            invalidRespond.setText("YOUR LENGTH OF FIELDS ARE TOO LONG!");
        }

        else if(Connection.checkUserSignUp(userNameInput.getText(),passInput.getText(),emailInput.getText())){
            if(Connection.getStatus()){
                try {
                    Parent logParent = null;
                    logParent = FXMLLoader.load(getClass().getResource("/ChooseHeroScreen.fxml"));
//                    logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Stage s1 = (Stage)((Node) event.getSource()).getScene().getWindow();

                    Scene logScene = new Scene(logParent);

//                    logStage.setScene(logScene);
//                    logStage.show();
                    s1.setScene(logScene);
                    s1.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                //Print error massage
                emptyRespond.setText("");
                invalidRespond.setText("");
                invalidEmail.setText("");
                try {
                    if (Connection.receiveSignUpSignIn() != null)
                        serverRespond.setText("SIGN IN FAILED!");
                } catch (IOException e) {
                    serverRespond.setText("CONNECTING TO SERVER FAILED");
                }

            }
        }

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
        /*if (!SplashController.isPlayed) {
            loadSplash();
            SplashController.isPlayed = true;
        }*/

    }
}
