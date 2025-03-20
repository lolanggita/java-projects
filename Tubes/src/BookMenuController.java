import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class BookMenuController {

    @FXML private Button dataBookButton;
    @FXML private Button borrowBookButton;
    @FXML private Button returnBookButton;
    @FXML private Button backButton;

    @FXML
    public void handleDataBook(ActionEvent event) throws IOException{
        navigateTo("dataBook.fxml", event);
    }

    @FXML
    public void handleBorrowBook(ActionEvent event) throws IOException{
        navigateTo("BorrowBook.fxml", event);
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException{
        navigateTo("home2.fxml", event);
    }

    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
