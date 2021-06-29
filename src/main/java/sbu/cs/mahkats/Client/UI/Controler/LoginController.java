package sbu.cs.mahkats.Client.UI.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sbu.cs.mahkats.Client.Connection.Connection;
import sbu.cs.mahkats.Configuration.Config;

import java.io.IOException;

public class LoginController  {
    private static Config config = Config.getInstance();
    private static final int USERNAME_LENGTH = config.getIntValue("input.limit.userName");
    private static final int PASSWORD_LENGTH = config.getIntValue("input.limit.passWord");

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField passInput;

    @FXML
    private Label emptyRespond;

    @FXML
    private Label invalidRespond;

    @FXML
    private Label serverRespond;

    @FXML
    void backAction(ActionEvent event) throws IOException {
        Parent logParent = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene logScene = new Scene(logParent);

        logStage.setScene(logScene);
        logStage.show();
    }

    @FXML
    void loginAction(ActionEvent event) {
        if (passInput.getText().isEmpty() || userNameInput.getText().isEmpty()){
            invalidRespond.setText("");
            emptyRespond.setText("PLEASE ENTER ALL FIELDS!");
        }
        else if (!goodInput(userNameInput.getText(),passInput.getText())){
            emptyRespond.setText("");
            invalidRespond.setText("DON'T USE ! , / , ? , ) , ( , * , & , % IN YOUR INPUT!");
        }
        else if (!checkLength(userNameInput.getText(),passInput.getText())){
            emptyRespond.setText("");
            serverRespond.setText("");
            invalidRespond.setText("YOUR LENGTH OF FIELDS ARE TOO LONG!");
        }

        else {
            try {
                if(Connection.checkUserSignIn(userNameInput.getText(),passInput.getText())){
                    if(Connection.getCheckStatus()){
                        Connection.runReceiver();
                        Parent logParent = FXMLLoader.load(getClass().getResource("/LoadingScreenOne.fxml"));
                        Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        Scene logScene = new Scene(logParent);

                        logStage.setScene(logScene);
                        logStage.show();
                    }


                    else {
                        emptyRespond.setText("");
                        invalidRespond.setText("");
                        try {
                            if (Connection.receive() != null)
                                serverRespond.setText("SIGN UP FAILED! Try later");
                        } catch (IOException e) {
                            serverRespond.setText("CONNECTING TO SERVER FAILED");
                        }
                    }
                }
            } catch (IOException e) {
                serverRespond.setText("SIGN UP FAILED! Try later");
                e.printStackTrace();
            }
        }
    }

    private boolean goodInput(String userName, String password){
        boolean userName_check, password_check;
        userName_check = checkString(userName);
        password_check = checkString(password);

        return userName_check && password_check;
    }

    private boolean checkLength(String userName , String passWord){
        return (userName.length() <= USERNAME_LENGTH) &&
                (passWord.length() <= PASSWORD_LENGTH);
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
}
