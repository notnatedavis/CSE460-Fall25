//   src/model/Appointment.java
package model;

//   imports
import java.sql.Date;

public class Appointment {
    public String appointmentID;
    public String patientID;
    public Date appointmentDate;

    private CTScanTechnician ctScanTechnician; // association
    private Receptionist receptionist; // association

    public void scheduleCTScan() {
        // logic here at some point , just a placeholder
    }

    public void provideAppointment() {
        // logic here at some point , just a placeholder
    }

    // add (constructor, getters, setters) at some point
}