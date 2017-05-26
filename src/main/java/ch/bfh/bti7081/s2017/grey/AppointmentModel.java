package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import com.vaadin.data.Binder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentModel {
    private AppointmentService appointmentService = new AppointmentServiceImpl();
    private PatientService patientService = new PatientServiceImpl();
    private StaffService staffService = new StaffServiceImpl();

    private Appointment appointment = new Appointment();
    private List<Appointment> appointmentList = new ArrayList<Appointment>();
    private Staff staff;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        this.appointment.setStaff(this.staff);
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void saveAppointment() {
        this.appointmentService.createAppointment(this.appointment.getPatient(), this.appointment.getStaff(), this.appointment.getDescription(), this.appointment.getTitle(), this.appointment.getDate(), this.appointment.getEndDate());
    }

    public void setUser(String username) {
        this.staff = staffService.findStaffByLogin(username);
        this.appointment.setStaff(this.staff);
        this.appointmentList = this.appointmentService.findAppointmentsByStaffAndDate(staff, LocalDate.now());
    }

    public List<Patient> getPatients() {
        return this.patientService.getAllPatients();
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
