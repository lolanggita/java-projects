import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class HomepageController {

    @FXML
    void handleBook(ActionEvent event) throws IOException{
        navigateTo("bookMenu.fxml", event);
    }

    @FXML
    void handleMember(ActionEvent event) throws IOException {
    navigateTo("member2.fxml", event);
    }

    

    @FXML
    private Button memberButton; 

    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
