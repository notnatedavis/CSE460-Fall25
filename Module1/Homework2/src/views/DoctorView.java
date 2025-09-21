//   src/views/DoctorView.java
package views;

//   imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import utils.NavigationController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DoctorView {
    public void show(Stage stage) {
        stage.setTitle("Doctor View");
        
        // input fields
        TextField patientIDField = new TextField();
        Button loadButton = new Button("Load Results");
        Button determineRiskButton = new Button("Determine Risk");
        Button backButton = new Button("Back to Main Menu");
        
        // result display fields
        Label totalScoreLabel = new Label("The total Agatston CAC score: ");
        Label lmLabel = new Label("LM: ");
        Label ladLabel = new Label("LAD: ");
        Label lcxLabel = new Label("LCX: ");
        Label rcaLabel = new Label("RCA: ");
        Label pdaLabel = new Label("PDA: ");
        
        // risk assessment panel
        VBox riskPanel = createRiskPanel();
        TextArea riskTextArea = (TextArea) riskPanel.getChildren().get(1); 
        // get the text area
        
        loadButton.setOnAction(e -> {
            String patientID = patientIDField.getText();
            
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
                riskTextArea.setText(""); // clear risk assessment
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
        
        determineRiskButton.setOnAction(e -> {
            // risk determination logic
            try {
                String totalScoreText = totalScoreLabel.getText().split(": ")[1];
                int totalScore = Integer.parseInt(totalScoreText);
                String riskAssessment; 

                if (totalScore == 0) {
                    riskAssessment = "Zero : No plaque. Your risk of heart attack is low.";
                } else if (totalScore >= 1 && totalScore <= 10) {
                    riskAssessment = "1 - 10 : Small amount of plaque. You have less than a 10 percent chance of having heart disease, and your risk of heart attack is low.";
                } else if (totalScore >= 11 && totalScore <= 100) {
                    riskAssessment = "11 - 100 : Some plaque. You have mild heart disease and a moderate chance of heart attack. Your doctor may recommend other treatment in addition to lifestyle changes.";
                } else if (totalScore >= 101 && totalScore <= 400) {
                    riskAssessment = "101 - 400: Moderate amount of plaque. You have heart disease and plaque may be blocking an artery. Your chance of having a heart attack is moderate to high. Your health professional may want more tests and may start treatment.";
                } else {
                    riskAssessment = "Over 400: Large amount of plaque. You have more than a 90 percent chance that plaque is blocking one of your arteries. Your chance of heart attack is high. Your health professional will want more tests and will start treatment.";
                }

                // display in risk panel
                riskTextArea.setText(riskAssessment);
            } catch (Exception ex) {
                riskTextArea.setText("Unable to determine risk. Please load valid results.");
            }
        });
        
        backButton.setOnAction(e -> NavigationController.showMainMenu());
        
        // create results panel
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
        
        // single column layout for vessel scores
        resultsGrid.add(lmLabel, 0, 2);
        resultsGrid.add(ladLabel, 0, 3);
        resultsGrid.add(lcxLabel, 0, 4);
        resultsGrid.add(rcaLabel, 0, 5);
        resultsGrid.add(pdaLabel, 0, 6);
        
        VBox resultsPanel = new VBox(20, inputGrid, resultsGrid, determineRiskButton, backButton);
        resultsPanel.setPadding(new javafx.geometry.Insets(20));
        
        // create main layout with results on left and risk panel on right
        HBox mainLayout = new HBox(20, resultsPanel, riskPanel);
        mainLayout.setPadding(new javafx.geometry.Insets(20));
        
        // make both panels expand to fill available space
        HBox.setHgrow(resultsPanel, Priority.ALWAYS);
        HBox.setHgrow(riskPanel, Priority.ALWAYS);
        
        Scene scene = new Scene(mainLayout, 800, 500); // increased width to fit both panels
        NavigationController.navigateTo(scene, "Doctor View");
    }

    private VBox createRiskPanel() {
        Label riskTitle = new Label("RISK");
        riskTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        TextArea riskTextArea = new TextArea();
        riskTextArea.setEditable(false);
        riskTextArea.setWrapText(true);
        riskTextArea.setPrefHeight(300);
        riskTextArea.setPromptText("Risk assessment will appear here after analysis");
        
        VBox riskPanel = new VBox(10, riskTitle, riskTextArea);
        riskPanel.setStyle("-fx-padding: 15; -fx-border-color: #ccc; -fx-border-width: 1; -fx-border-radius: 5;");
        
        return riskPanel;
    }
}