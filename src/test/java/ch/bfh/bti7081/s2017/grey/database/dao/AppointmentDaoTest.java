package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Quentin
 */
public class AppointmentDaoTest {

    @Test
    public void testCreateAppointment() {
        AppointmentDao appointmentDao = new AppointmentDao();
        StaffDao staffDao = new StaffDao();
        PatientDao patientDao = new PatientDao();

        LocalDateTime date = LocalDateTime.now();
        Staff staff = staffDao.getStaffByLogin("vonaj2");
        Patient patient = patientDao.getPatientByName("Test", "Test");

        appointmentDao.createAppointment(date, "appointment", "test for dao", staff, patient);

        List<Appointment> appointments = appointmentDao.findAppointmentsForStaffAndDay(staff, date.toLocalDate());
        assertNotNull(appointments.get(0));

        for (Appointment appointment : appointments) {
            appointmentDao.removeAppointment(appointment.getId());
        }

    }

}
