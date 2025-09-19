//   src/views/DoctorView.java
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DoctorView {
    public void show(Stage stage) {
        stage.setTitle("Doctor View");
        
        TextField patientIDField = new TextField();
        Button loadButton = new Button("Load Results");
        Button determineRiskButton = new Button("Determine Risk");
        Button backButton = new Button("Back to Main Menu");
        
        // Result display fields
        Label totalScoreLabel = new Label("The total Agatston CAC score: ");
        Label lmLabel = new Label("LM: ");
        Label ladLabel = new Label("LAD: ");
        Label lcxLabel = new Label("LCX: ");
        Label rcaLabel = new Label("RCA: ");
        Label pdaLabel = new Label("PDA: ");
        Label riskLabel = new Label("Risk Level: ");
        
        loadButton.setOnAction(e -> {
            String patientID = patientIDField.getText();
            
            // Load CT results
            try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_CTResults.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Total CAC Score:")) {
                        totalScoreLabel.setText("The total Agatston CAC score: " + line.split(": ")[1]);
                    } else if (line.startsWith("LM:")) {
                        lmLabel.setText("LM: " + line.split(": ")[1]);
                    } else if (line.startsWith("LAD:")) {
                        ladLabel.setText("LAD: " + line.split(": ")[1]);
                    } else if (line.startsWith("LCX:")) {
                        lcxLabel.setText("LCX: " + line.split(": ")[1]);
                    } else if (line.startsWith("RCA:")) {
                        rcaLabel.setText("RCA: " + line.split(": ")[1]);
                    } else if (line.startsWith("PDA:")) {
                        pdaLabel.setText("PDA: " + line.split(": ")[1]);
                    }
                }
                riskLabel.setText("Risk Level: "); // Reset risk level
            } catch (IOException ex) {
                // Results not found, clear labels
                totalScoreLabel.setText("The total Agatston CAC score: ");
                lmLabel.setText("LM: ");
                ladLabel.setText("LAD: ");
                lcxLabel.setText("LCX: ");
                rcaLabel.setText("RCA: ");
                pdaLabel.setText("PDA: ");
                riskLabel.setText("Risk Level: ");
            }
        });
        
        determineRiskButton.setOnAction(e -> {
            // Simple risk determination based on total CAC score
            try {
                String totalScoreText = totalScoreLabel.getText().split(": ")[1];
                int totalScore = Integer.parseInt(totalScoreText);
                
                if (totalScore == 0) {
                    riskLabel.setText("Risk Level: No identifiable plaque. Very low risk.");
                } else if (totalScore < 100) {
                    riskLabel.setText("Risk Level: Mild plaque. Low to moderate risk.");
                } else if (totalScore < 400) {
                    riskLabel.setText("Risk Level: Moderate plaque. Moderate to high risk.");
                } else {
                    riskLabel.setText("Risk Level: Extensive plaque. High risk.");
                }
            } catch (Exception ex) {
                riskLabel.setText("Risk Level: Unable to determine risk. Please load valid results.");
            }
        });
        
        backButton.setOnAction(e -> NavigationController.showMainMenu());
        
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(new Label("Patient ID:"), 0, 0);
        inputGrid.add(patientIDField, 1, 0);
        inputGrid.add(loadButton, 2, 0);
        
        GridPane resultsGrid = new GridPane();
        resultsGrid.setHgap(10);
        resultsGrid.setVgap(10);
        resultsGrid.add(totalScoreLabel, 0, 0, 2, 1);
        resultsGrid.add(new Label("Vessel level Agatston CAC score:"), 0, 1, 2, 1);
        resultsGrid.add(lmLabel, 0, 2);
        resultsGrid.add(ladLabel, 1, 2);
        resultsGrid.add(lcxLabel, 0, 3);
        resultsGrid.add(rcaLabel, 1, 3);
        resultsGrid.add(pdaLabel, 0, 4);
        resultsGrid.add(riskLabel, 0, 5, 2, 1);
        
        VBox layout = new VBox(20, inputGrid, resultsGrid, determineRiskButton, backButton);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        Scene scene = new Scene(layout, 500, 500);
        NavigationController.navigateTo(scene, "Doctor View");
    }
}