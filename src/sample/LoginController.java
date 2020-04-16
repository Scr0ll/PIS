package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends WSDLRequest
{
    @FXML
    TextField emailField;
    @FXML PasswordField passwordField;
    @FXML Label infoLabel;
    @FXML Button resetPasswordButton;
    @FXML Button loginButton;

    private int customerID;

    public void Login()
    {
        String email = emailField.getText();
        String password = passwordField.getText();


        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                infoLabel.setText("");
            }
        });
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                infoLabel.setText("");
            }
        });

        if(CheckLoginData(email, password))
        {
            try{
                System.out.println("open main window");
                /*FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
                Parent root = (Parent) loader.load();
                TODO controller hlavneho menu pre posunutie ID/email pouzivatela
                TODO MainController controller = loader.getController();
                TODO controller.LoadCustomerInfo(customerID);
                Stage stage = new Stage();
                stage.setTitle("Main window");
                stage.setScene(new Scene(root));

                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                currentStage.close();
                stage.show();*/

            }catch(Exception e){
                System.out.println(e);
            }
        }
        else
        {
            infoLabel.setText("Incorrect login data");
        }
    }

    public void OpenResetPasswordWindow()
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Reset Password");
            stage.setScene(new Scene(root, 300, 115));
            stage.show();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    boolean CheckLoginData(String email, String password)
    {
        String userInfo = GetUserByEmail(email);
        if(userInfo.length() == 0)
        {
            return false;
        }
        String correctPassword = "";
        String id = "";
        int index = userInfo.indexOf("<password>") + "<password>".length();
        while(userInfo.toCharArray()[index] != '<')
        {
            correctPassword += userInfo.toCharArray()[index];
            index++;
        }
        if(password.equals(correctPassword))
        {
            index = userInfo.indexOf("<id>") + "<id>".length();
            while(userInfo.toCharArray()[index] != '<')
            {
                id += userInfo.toCharArray()[index];
                index++;
            }
            customerID = Integer.parseInt(id);
            return true;
        }
        else
        {
            return false;
        }
    }
}
