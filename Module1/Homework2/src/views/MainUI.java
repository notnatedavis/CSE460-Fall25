//   src/views/MainUI.java
package views;

//   imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.NavigationController;

public class MainUI extends Application {
    private Stage primaryStage;
    private Scene mainMenuScene;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Heart Health Imaging and Recording System");
        
        // set up navigation controller
        NavigationController.setPrimaryStage(primaryStage);
        
        showMainMenu();
    }
    
    public void showMainMenu() { // main entry page
        Text title = new Text("Welcome to Heart Health Imaging and Recording System");
        title.setFont(Font.font(18));
        
        Button patientIntakeBtn = new Button("Patient Intake");
        patientIntakeBtn.setOnAction(e -> {
            PatientIntakeView patientIntakeView = new PatientIntakeView();
            patientIntakeView.show(primaryStage);
        });
        
        Button ctScanTechBtn = new Button("CT Scan Tech View");
        ctScanTechBtn.setOnAction(e -> {
            CTScanTechView ctScanTechView = new CTScanTechView();
            ctScanTechView.show(primaryStage);
        });
        
        Button patientViewBtn = new Button("Patient View");
        patientViewBtn.setOnAction(e -> {
            PatientView patientView = new PatientView();
            patientView.show(primaryStage);
        });
        
        Button doctorViewBtn = new Button("Doctor View");
        doctorViewBtn.setOnAction(e -> {
            DoctorView doctorView = new DoctorView();
            doctorView.show(primaryStage);
        });
        
        VBox layout = new VBox(20, title, patientIntakeBtn, ctScanTechBtn, patientViewBtn, doctorViewBtn);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        
        mainMenuScene = new Scene(layout, 500, 400);
        NavigationController.setMainMenuScene(mainMenuScene);
        
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}