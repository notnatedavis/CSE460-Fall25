//   src/utils/NavigationController.java
package utils;

//   imports
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationController {
    private static Stage primaryStage;
    private static Scene mainMenuScene;
    
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public static void setMainMenuScene(Scene scene) {
        mainMenuScene = scene;
    }
    
    public static void showMainMenu() {
        if (primaryStage != null && mainMenuScene != null) {
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Heart Health Imaging and Recording System");
        }
    }
    
    public static void navigateTo(Scene newScene, String title) {
        if (primaryStage != null) {
            primaryStage.setScene(newScene);
            primaryStage.setTitle(title);
        }
    }
}