//   src/model/Appointment.java
package model;

//   imports
import java.sql.Date;

public class Appointment {
    public String appointmentID;
    public String patientID;
    public Date appointmentDate;

    private CTScanTechnician ctScanTechnician; // Association
    private Receptionist receptionist; // Association

    public void scheduleCTScan() {
        // logic here
    }

    public void provideAppointment() {
        // logic here
    }

    // add (constructor, getters, setters) at some point
}