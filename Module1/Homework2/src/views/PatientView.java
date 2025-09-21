//   src/views/PatientView.java
package views;

import javafx.geometry.Pos;
//   imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.NavigationController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatientView {
    public void show(Stage stage) {
        stage.setTitle("Patient View");
        
        TextField patientIDField = new TextField();
        Button loadButton = new Button("Load Results");
        Button backButton = new Button("Back to Main Menu");
        
        // result display fields
        Label patientNameLabel = new Label("Hello Patient");
        Label totalScoreLabel = new Label("The total Agatston CAC score: ");
        Label lmLabel = new Label("LM: ");
        Label ladLabel = new Label("LAD: ");
        Label lcxLabel = new Label("LCX: ");
        Label rcaLabel = new Label("RCA: ");
        Label pdaLabel = new Label("PDA: ");

        // center the patient name label
        patientNameLabel.setAlignment(Pos.CENTER);
        HBox centeredNameBox = new HBox(patientNameLabel);
        centeredNameBox.setAlignment(Pos.CENTER);
        
        loadButton.setOnAction(e -> {
            String patientID = patientIDField.getText();
            
            // load patient info
            try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_PatientInfo.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("First Name:") || line.startsWith("Last Name:")) {
                        String[] parts = line.split(": ");
                        if (parts.length > 1) {
                            patientNameLabel.setText("Hello " + parts[1]);
                        }
                    }
                }
            } catch (IOException ex) {
                patientNameLabel.setText("Patient not found");
            }
            
            // load CT results
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
            } catch (IOException ex) {
                // results not found, clear labels
                totalScoreLabel.setText("The total Agatston CAC score: ");
                lmLabel.setText("LM: ");
                ladLabel.setText("LAD: ");
                lcxLabel.setText("LCX: ");
                rcaLabel.setText("RCA: ");
                pdaLabel.setText("PDA: ");
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

        // use the centered name box instead of the label directly
        resultsGrid.add(centeredNameBox, 0, 0, 2, 1);
        resultsGrid.add(totalScoreLabel, 0, 1, 2, 1);

        // single column layout for vessel scores
        resultsGrid.add(lmLabel, 0, 2);
        resultsGrid.add(ladLabel, 0, 3);
        resultsGrid.add(lcxLabel, 0, 4);
        resultsGrid.add(rcaLabel, 0, 5);
        resultsGrid.add(pdaLabel, 0, 6);
        
        VBox layout = new VBox(20, inputGrid, resultsGrid, backButton);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        Scene scene = new Scene(layout, 500, 500);
        NavigationController.navigateTo(scene, "Patient View");
    }
}