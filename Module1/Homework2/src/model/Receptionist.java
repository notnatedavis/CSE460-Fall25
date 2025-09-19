//   src/model/Receptionist.java
package model;

public class Receptionist extends User{
    private CTTest ctTest; // Aggregation to CTTest
    private PatientRecord[] patientRecords; // Aggregation to PatientRecord
    private Appointment[] appointments; // Aggregation to Appointment
    
    public void inputPatientInformation() {
        // logic here
    }

    public void scheduleExam() {
        // logic here
    }

    public void generatePatientID() {
        // logic here
    }
}