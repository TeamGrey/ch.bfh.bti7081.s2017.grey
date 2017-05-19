package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import com.vaadin.data.Binder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentModel {
    private Binder<Appointment> binder = new Binder<>(Appointment.class);
    private Appointment appointment = new Appointment();
    private List<Appointment> appointmentList = new ArrayList<Appointment>();

    public void setView(Object view) {
        binder.bindInstanceFields(view);
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        binder.setBean(appointment);
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
