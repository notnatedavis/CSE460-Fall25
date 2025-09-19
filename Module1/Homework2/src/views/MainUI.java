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

public class MainUI extends Application {
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Heart Health Imaging and Recording System");
        
        showMainMenu();
    }
    
    public void showMainMenu() {
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
        
        primaryStage.setScene(new Scene(layout, 500, 400));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}