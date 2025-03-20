import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML 
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void LoginFunction(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();
 
        Parent menuParent = FXMLLoader.load(getClass().getResource("home2.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (username.equals("admin") && password.equals("12345")) {
            stage.setScene(menuScene);
            stage.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Username atau Password salah");
            alert.setContentText("Silahkan cek data yang anda masukkan");

            alert.showAndWait();
        }       
    }
}