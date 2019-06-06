 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Untitled.fxml"));
        primaryStage.setTitle("Coffee Dungeon");
        primaryStage.setScene(new Scene(root, 1024, 768));

        primaryStage.show();
        primaryStage.setResizable(false);

    }

    //https://stackoverflow.com/questions/41419590/use-progressbar-made-with-fxml-scenebuilder-to-use-values-from-a-method
    //https://stackoverflow.com/questions/13357077/javafx-progressbar-how-to-change-bar-color
    public static void main(String[] args) {
        launch(args);
    }
}
