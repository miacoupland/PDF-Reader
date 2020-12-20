import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PDF Reader");
        Controller c = new Controller();
        primaryStage.setScene(c.addComponents());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
TODO:
 - Add colour schemes
 - Save reading progress
 */