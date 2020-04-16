package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class ResetPasswordController extends WSDLRequest
{
    @FXML TextField emailField;
    @FXML Label infoLabel;
    @FXML Button sendRequestButton;

    private String userInfo;

    public void SendRequest()
    {
        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                infoLabel.setText("");
            }
        });

        String email = emailField.getText();
        if(CheckEmail(email))
        {
            String id = "";
            int customerID;
            String name = "";
            int index = userInfo.indexOf("<id>") + "<id>".length();
            while(userInfo.toCharArray()[index] != '<')
            {
                id += userInfo.toCharArray()[index];
                index++;
            }
            customerID = Integer.parseInt(id);

            index = userInfo.indexOf("<name>") + "<name>".length();
            while(userInfo.toCharArray()[index] != '<')
            {
                name += userInfo.toCharArray()[index];
                index++;
            }

            String newPassword = "";
            for(int i = 0; i < 8; i++)
            {
                Random r = new Random();
                newPassword += (char)(r.nextInt((122 - 97) + 1) + 97);
            }

            UpdatePassword(customerID, name, email, newPassword);

            //SendEmail(email, newPassword);

            infoLabel.setTextFill(Color.web("#00FF00"));
            infoLabel.setText("Password Changed. Check email");

        }
        else
        {
            infoLabel.setTextFill(Color.web("#FF0000"));
            infoLabel.setText("Account with this e-mail doesn't exist");
        }
    }

    boolean CheckEmail(String email)
    {
        userInfo = GetUserByEmail(email);
        if(userInfo.length() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
