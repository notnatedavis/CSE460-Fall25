//   src/views/CTScanTechView.java
package views;

//   imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.NavigationController;

import java.io.FileWriter;
import java.io.IOException;

public class CTScanTechView {
    public void show(Stage stage) {
        stage.setTitle("CT Scan Technician View");
        
        TextField patientID = new TextField();
        TextField totalScore = new TextField();
        TextField lmScore = new TextField();
        TextField ladScore = new TextField();
        TextField lcxScore = new TextField();
        TextField rcaScore = new TextField();
        TextField pdaScore = new TextField();
        
        Button saveButton = new Button("Save Results");
        Button backButton = new Button("Back to Main Menu");
        
        saveButton.setOnAction(e -> {
            // Save CT results to file
            try (FileWriter writer = new FileWriter(patientID.getText() + "_CTResults.txt")) {
                writer.write("Patient ID: " + patientID.getText() + "\n");
                writer.write("Total CAC Score: " + totalScore.getText() + "\n");
                writer.write("LM: " + lmScore.getText() + "\n");
                writer.write("LAD: " + ladScore.getText() + "\n");
                writer.write("LCX: " + lcxScore.getText() + "\n");
                writer.write("RCA: " + rcaScore.getText() + "\n");
                writer.write("PDA: " + pdaScore.getText() + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Clear fields after saving
            patientID.clear();
            totalScore.clear();
            lmScore.clear();
            ladScore.clear();
            lcxScore.clear();
            rcaScore.clear();
            pdaScore.clear();
        });
        
        backButton.setOnAction(e -> NavigationController.showMainMenu());
        
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(new Label("Patient ID:"), 0, 0);
        formGrid.add(patientID, 1, 0);
        formGrid.add(new Label("The total Agatston CAC score:"), 0, 1);
        formGrid.add(totalScore, 1, 1);
        formGrid.add(new Label("Vessel level Agatston CAC score"), 0, 2);
        formGrid.add(new Label(""), 1, 2); // Empty cell for spacing
        formGrid.add(new Label("LM:"), 0, 3);
        formGrid.add(lmScore, 1, 3);
        formGrid.add(new Label("LAD:"), 0, 4);
        formGrid.add(ladScore, 1, 4);
        formGrid.add(new Label("LCX:"), 0, 5);
        formGrid.add(lcxScore, 1, 5);
        formGrid.add(new Label("RCA:"), 0, 6);
        formGrid.add(rcaScore, 1, 6);
        formGrid.add(new Label("PDA:"), 0, 7);
        formGrid.add(pdaScore, 1, 7);
        
        VBox layout = new VBox(20, formGrid, saveButton, backButton);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        Scene scene = new Scene(layout, 500, 500);
        NavigationController.navigateTo(scene, "CT Scan Technician View");
    }
}