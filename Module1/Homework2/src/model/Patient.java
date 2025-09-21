//   src/model/Patient.java
package model;

public class Patient extends User{
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String healthHistory;
    public String insuranceID;

    private PatientRecord patientRecord; // aggregation to PatientRecord
    private CTTest ctTest; // aggregation to CTTest

    public void viewCTScanResults() {
        // logic to view CT scan results
    }

    public void login() {
        // login logic here at some point , just a placeholder
    }

    // add (constructor, getters, setters) at some point
}