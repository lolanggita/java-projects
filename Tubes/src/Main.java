import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Bektel Library");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    public static void main(String[]  args) throws Exception  {
        launch(args);
    }
}
