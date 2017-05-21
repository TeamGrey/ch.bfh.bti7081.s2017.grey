package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public interface AppointmentView {
    interface AppontmentViewListener {
        void saveClick();
        void editClick();
    }
    public void addListener(AppontmentViewListener listener);
    public void setPatients(List<Patient> patients);
    public void setAppointment(Appointment appointment);
    public void setAppointmentList(List<Appointment> appointmentList);
}
