package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.AppointmentDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class AppointmentServiceImpl implements AppointmentService {

  private AppointmentDao dao;

  public AppointmentServiceImpl(EntityManager em) {
    dao = new AppointmentDao(em);
  }


  /**
   * Finds all appointment of one staff and a date range
   *
   * @param staff Staff whose appointments are wanted
   * @param start Start of date range
   * @param end End of date range
   * @return List of appointments
   */
  @Override
  public List<Appointment> findAppointmentsByStaffAndDateRange(Staff staff, LocalDateTime start,
      LocalDateTime end) {
    return dao.findAppointmentsForStaffAndDateRange(staff, start, end);
  }

  /**
   * Finds all appointment of one staff and a specific date
   *
   * @param staff Staff whose appointments are wanted
   * @param date Date of the appointments
   * @return List of appointments
   */
  @Override
  public List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date) {
    return dao.findAppointmentsForStaffAndDay(staff, date);
  }

  /**
   * Find an appointment by it's id
   *
   * @param id Id of the appointment
   * @return Appointment if found
   */
  @Override
  public Appointment getAppointmentById(long id) {
    return dao.find(id);
  }

  /**
   * Creates a new appointment
   *
   * @param patient Patient with whom the appointment is
   * @param staff Staff who is assigned to the appointment
   * @param description Description of the appointment
   * @param title Title of the appointment
   * @param date Start date of the appointment
   * @param end End date of the appointment
   */
  @Override
  public Appointment createAppointment(Patient patient, Staff staff, String description,
      String title, LocalDateTime date, LocalDateTime end) {
    Instant instant = Instant.now();
    Appointment appointment = new Appointment();
    appointment.setDate(date);
    appointment.setEndDate(end);
    appointment.setTitle(title);
    appointment.setDescription(description);
    appointment.setStaff(staff);
    appointment.setPatient(patient);
    appointment.setCreated(new Timestamp(instant.toEpochMilli()));
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    appointment.create();

    return dao.create(appointment);
  }

  /**
   * Delay an appointment
   *
   * @param appointment Appointment to be delayed
   * @param newDate New start date of the appointment
   * @param newEnd New end date of the appointment
   * @return Delayed appointment
   */
  @Override
  public Appointment delayAppointment(Appointment appointment, LocalDateTime newDate,
      LocalDateTime newEnd) {
    Instant instant = Instant.now();
    appointment.setDate(newDate);
    appointment.setEndDate(newEnd);
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    appointment.delay();

    return dao.update(appointment);
  }

  /**
   * Cancel an appointment
   *
   * @param appointment Appointment to be canceled
   */
  @Override
  public void cancelAppointment(Appointment appointment) {
    Instant instant = Instant.now();
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    appointment.cancel();

    dao.update(appointment);
  }

  /**
   * Finish an appointment
   *
   * @param appointment Appointment to be finished
   * @param finished Time when it was finished
   * @param delay Delay with which it was finished
   */
  @Override
  public void finishAppointment(Appointment appointment, LocalDateTime finished, int delay) {
    Instant instant = Instant.now();
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    appointment.setFinished(Timestamp.valueOf(finished));
    appointment.setDelay(delay);
    appointment.finish();

    dao.update(appointment);
  }

  /**
   * Deletes an appointment
   *
   * @param appointment Appointment to be deleted
   */
  @Override
  public void deleteAppointment(Appointment appointment) {
    dao.delete(appointment.getId());
  }

  /**
   * Updates an appointment
   *
   * @param appointment Appointment to be updated
   */
  @Override
  public void editAppointment(Appointment appointment) {
    Instant instant = Instant.now();
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    dao.update(appointment);
  }
}
