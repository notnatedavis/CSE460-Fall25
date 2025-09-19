//   src/MainApp.java

// imports
import javafx.application.Application;
import javafx.stage.Stage;
import views.MainUI;

public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MainUI mainUI = new MainUI();
        mainUI.start(primaryStage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}