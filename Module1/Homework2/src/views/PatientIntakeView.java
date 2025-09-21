//   src/views/PatientIntakeView.java
package views;

//   imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PatientRecord;
import utils.NavigationController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PatientIntakeView {
    public void show(Stage stage) {
        stage.setTitle("Patient Intake");
        
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        TextField history = new TextField();
        TextField insurance = new TextField();
        
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back to Main Menu");
        
        saveButton.setOnAction(e -> {
            // generate random 5-digit patientID
            Random random = new Random();
            int randomNumber = random.nextInt(90000) + 10000; // generates a number between [10000 - 99999]
            String patientID = String.valueOf(randomNumber);

            PatientRecord record = new PatientRecord();
            record.patientID = patientID;
            record.firstName = firstName.getText();
            record.lastName = lastName.getText();
            record.email = email.getText();
            record.phoneNumber = phone.getText();
            record.healthHistory = history.getText();
            record.insuranceID = insurance.getText();
            
            // save to file
            try (FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt")) {
                writer.write("Patient ID: " + patientID + "\n");
                writer.write("First Name: " + record.firstName + "\n");
                writer.write("Last Name: " + record.lastName + "\n");
                writer.write("Email: " + record.email + "\n");
                writer.write("Phone Number: " + record.phoneNumber + "\n");
                writer.write("Health History: " + record.healthHistory + "\n");
                writer.write("Insurance ID: " + record.insuranceID + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            NavigationController.showMainMenu();
        });
        
        backButton.setOnAction(e -> NavigationController.showMainMenu());
        
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(new Label("First Name:"), 0, 0);
        formGrid.add(firstName, 1, 0);
        formGrid.add(new Label("Last Name:"), 0, 1);
        formGrid.add(lastName, 1, 1);
        formGrid.add(new Label("Email:"), 0, 2);
        formGrid.add(email, 1, 2);
        formGrid.add(new Label("Phone Number:"), 0, 3);
        formGrid.add(phone, 1, 3);
        formGrid.add(new Label("Health History:"), 0, 4);
        formGrid.add(history, 1, 4);
        formGrid.add(new Label("Insurance ID:"), 0, 5);
        formGrid.add(insurance, 1, 5);
        
        VBox layout = new VBox(20, formGrid, saveButton, backButton);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        Scene scene = new Scene(layout, 500, 500);
        NavigationController.navigateTo(scene, "Patient Intake");
    }
}