package ch.bfh.bti7081.s2017.grey.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.database.util.JPAHibernateTest;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gabor on 29/05/17.
 */
public class AppointmentServiceTest extends JPAHibernateTest {

  private AppointmentService appointmentService;
  private Appointment appointment;

  @Before
  public void setup() {
    appointmentService = new AppointmentServiceImpl(em);
    StaffService staffService = new StaffServiceImpl(em);
    PatientService patientService = new PatientServiceImpl(em);

    LocalDateTime date = LocalDateTime.now();
    LocalDateTime end = date.plusHours(1);
    Staff staff = staffService.findStaffByLogin("vonaj2");
    Patient patient = patientService.getPatientByName("Test", "Test");

    appointment = appointmentService.createAppointment(patient, staff, "test", "title", date, end);
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
    assertEquals(date, appointment.getDate());
    assertEquals(end, appointment.getEndDate());
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
