//   src/model/Patient.java
package model;

public class Patient extends User{
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String healthHistory;
    public String insuranceID;

    private PatientRecord patientRecord; // Aggregation to PatientRecord
    private CTTest ctTest; // Aggregation to CTTest

    public void viewCTScanResults() {
        // logic to view CT scan results
    }

    public void login() {
        // login logic here
    }

    // add (constructor, getters, setters) at some point
}