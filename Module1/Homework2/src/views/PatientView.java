//   src/views/PatientView.java
package views;

//   imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatientView {
    public void show(Stage stage) {
        stage.setTitle("Patient View");
        
        TextField patientIDField = new TextField();
        Button loadButton = new Button("Load Results");
        Button backButton = new Button("Back to Main Menu");
        
        // Result display fields
        Label patientNameLabel = new Label("Hello Patient");
        Label totalScoreLabel = new Label("The total Agatston CAC score: ");
        Label lmLabel = new Label("LM: ");
        Label ladLabel = new Label("LAD: ");
        Label lcxLabel = new Label("LCX: ");
        Label rcaLabel = new Label("RCA: ");
        Label pdaLabel = new Label("PDA: ");
        
        loadButton.setOnAction(e -> {
            String patientID = patientIDField.getText();
            
            // Load patient info
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
            } catch (IOException ex) {
                // Results not found, clear labels
                totalScoreLabel.setText("The total Agatston CAC score: ");
                lmLabel.setText("LM: ");
                ladLabel.setText("LAD: ");
                lcxLabel.setText("LCX: ");
                rcaLabel.setText("RCA: ");
                pdaLabel.setText("PDA: ");
            }
        });
        
        backButton.setOnAction(e -> showMainMenu(stage));
        
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(new Label("Patient ID:"), 0, 0);
        inputGrid.add(patientIDField, 1, 0);
        inputGrid.add(loadButton, 2, 0);
        
        GridPane resultsGrid = new GridPane();
        resultsGrid.setHgap(10);
        resultsGrid.setVgap(10);
        resultsGrid.add(patientNameLabel, 0, 0, 2, 1);
        resultsGrid.add(totalScoreLabel, 0, 1, 2, 1);
        resultsGrid.add(new Label("Vessel level Agatston CAC score:"), 0, 2, 2, 1);
        resultsGrid.add(lmLabel, 0, 3);
        resultsGrid.add(ladLabel, 1, 3);
        resultsGrid.add(lcxLabel, 0, 4);
        resultsGrid.add(rcaLabel, 1, 4);
        resultsGrid.add(pdaLabel, 0, 5);
        
        VBox layout = new VBox(20, inputGrid, resultsGrid, backButton);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        stage.setScene(new Scene(layout, 500, 400));
        stage.show();
    }
    
    private void showMainMenu(Stage stage) {
        MainUI mainUI = new MainUI();
        mainUI.showMainMenu();
    }
}