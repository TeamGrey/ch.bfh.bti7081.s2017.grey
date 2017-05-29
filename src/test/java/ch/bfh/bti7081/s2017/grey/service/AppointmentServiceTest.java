package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import static org.junit.Assert.*;

/**
 * Created by gabor on 29/05/17.
 */
public class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private Appointment appointment;

    @Before
    public void setup() {
        appointmentService = new AppointmentServiceImpl();
        StaffService staffService = new StaffServiceImpl();
        PatientService patientService = new PatientServiceImpl();

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime end = date.plusHours(1);
        Staff staff = staffService.findStaffByLogin("vonaj2");
        Patient patient = patientService.getPatientByName("Test", "Test");

        appointment = appointmentService.createAppointment(patient, staff, "test", "title", date, end);
    }

    @After
    public void cleanUp() {
        appointmentService.deleteAppointment(appointment);
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
        appointmentService.delayAppointment(appointment, date, end);

        assertEquals(AppointmentStatus.DELAYED, appointment.getStatus());
        assertEquals(date, appointment.getDate().toLocalDateTime());
        assertEquals(end, appointment.getEndDate().toLocalDateTime());
    }

    @Test
    public void testCancelAppointment() {
        appointmentService.cancelAppointment(appointment);

        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
    }

    @Test
    public void testFinishAppointment() {
        LocalDateTime finished = LocalDateTime.now();
        int delay = 30;
        appointmentService.finishAppointment(appointment, finished, delay);

        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
        assertEquals(finished, appointment.getFinished().toLocalDateTime());
        assertEquals(delay, appointment.getDelay());
    }
}
