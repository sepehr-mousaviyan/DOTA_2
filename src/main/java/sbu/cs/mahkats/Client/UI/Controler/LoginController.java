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

public class LoginController {
    Config config = Config.getInstance();
    private static String USERNAME_LENGTH = "input.limit.userName";
    private static String PASSWORD_LENGTH = "input.limit.passWord";

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
            emptyRespond.setText("PLEASE ENTER ALL FEILDS!");
        }
        if (!goodInput(userNameInput.getText(),passInput.getText())){
            emptyRespond.setText("");
            invalidRespond.setText("DON'T USE ! , / , ? , ) , ( , * , & , % IN YOUR INPUT!");
        }

        else if(Connection.checkUserSignIn(userNameInput.getText(),passInput.getText())){
            if(Connection.getCheckStatus()){
                //TODO: go to menu and list of heroes
            }


            else {
                emptyRespond.setText("");
                invalidRespond.setText("");
                try {
                    if (Connection.receive() != null)
                        serverRespond.setText("SIGN IN FAILED!");
                } catch (IOException e) {
                    serverRespond.setText("CONNECTING TO SERVER FAILED");
                }
            }
        }
    }

    private boolean goodInput(String userName, String password){
        boolean userName_check, password_check;
        userName_check = checkString(userName);
        password_check = checkString(password);

        return userName_check && password_check;
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
