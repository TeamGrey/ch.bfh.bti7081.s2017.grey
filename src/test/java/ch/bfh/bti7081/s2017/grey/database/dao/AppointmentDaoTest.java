package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Quentin
 */
public class AppointmentDaoTest {
    private AppointmentDao appointmentDao;
    private Appointment appointment;

    @Before
    public void setup() {
        appointmentDao = new AppointmentDao();
        StaffDao staffDao = new StaffDao();
        PatientDao patientDao = new PatientDao();

        // given
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime end = date.plusHours(1);
        Staff staff = staffDao.getStaffByLogin("vonaj2");
        Patient patient = patientDao.getPatientByName("Test", "Test");

        // when
        appointmentDao.createAppointment(date, end, "appointment", "test for dao", staff, patient);

        // then
        List<Appointment> appointments = appointmentDao.findAppointmentsForStaffAndDay(staff, date.toLocalDate());
        appointment = appointments.get(0);
    }

    @After
    public void cleanUp() {
        appointmentDao.removeAppointment(appointment.getId());
    }

    @Test
    public void testCreateAppointment() {
        assertNotNull(appointment);
        assertEquals(AppointmentStatus.CREATED, appointment.getStatus());
    }

    @Test
    public void testDelayAppointment() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime end = date.plusHours(1);
        appointmentDao.delayAppointment(appointment.getId(), date, end);

        assertEquals(AppointmentStatus.DELAYED, appointment.getStatus());
        assertEquals(date, appointment.getDate());
        assertEquals(end, appointment.getEndDate());
    }

    @Test
    public void testCancelAppointment() {
        appointmentDao.cancelAppointment(appointment.getId());

        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
    }

    @Test
    public void testFinishAppointment() {
        LocalDateTime finished = LocalDateTime.now();
        int delay = 30;
        appointmentDao.finishAppointment(appointment.getId(), finished, delay);

        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
        assertEquals(finished, appointment.getFinished().toLocalDateTime());
        assertEquals(delay, appointment.getDelay());
    }
}
