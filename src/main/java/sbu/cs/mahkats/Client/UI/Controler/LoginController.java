package sbu.cs.mahkats.Client.UI.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sbu.cs.mahkats.Client.Connection.Connection;
import sbu.cs.mahkats.Configuration.Config;

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
    void loginAction(ActionEvent event) {
        if (passInput.getText().isEmpty() || userNameInput.getText().isEmpty()){
            invalidRespond.setText("");
            emptyRespond.setText("PLEASE ENTER ALL FEILDS!");
        }
        if (!goodInput(userNameInput.getText(),passInput.getText())){
            emptyRespond.setText("");
            invalidRespond.setText("DON'T USE ! , / , ? , ) , ( , * , & , % IN YOUR INPUT!");
        }

        else if(Connection.checkUserlogIn(userNameInput.getText(),passInput.getText())){
            //وارد ص منیو می شود
        }
         // ************************

      //  else
      // پبام سرور را در لیبل چاپ میکند
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
