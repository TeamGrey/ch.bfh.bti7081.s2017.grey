package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.Date;
import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public interface AppointmentView {
    interface AppontmentViewListener {
        void newClick();
        void saveClick();
        void editClick();

        void appointmentMove(Date start);
        void appointmentReisize(Date start, Date end);

        void monthViewSelect();
        void weekViewSelect();
        void dayViewSelect();
        void dateSelect(Date date);
        void dateRangeSelect(Date start, Date end);
        void appointmentSelect(Appointment appointment);

        void viewEntered(String username);
    }
    public void addListener(AppontmentViewListener listener);
    public void setPatients(List<Patient> patients);
    public void setAppointment(Appointment appointment, boolean isEditMode);
    public void setAppointmentList(List<Appointment> appointmentList);
    public void setStartDate(Date startDate);
    public void setEndDate(Date endDate);
}
