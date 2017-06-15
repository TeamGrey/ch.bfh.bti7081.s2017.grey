package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.AppointmentDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;

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


  /** @see AppointmentService#findAppointmentsByStaffAndDateRange(Staff, LocalDateTime, LocalDateTime) */
  @Override
  public List<Appointment> findAppointmentsByStaffAndDateRange(Staff staff, LocalDateTime start,
      LocalDateTime end) {
    return dao.findAppointmentsForStaffAndDateRange(staff, start, end);
  }

  /** @see AppointmentService#findAppointmentsByStaffAndDate(Staff, LocalDate) */
  @Override
  public List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date) {
    return dao.findAppointmentsForStaffAndDay(staff, date);
  }

  /** @see AppointmentService#getAppointmentById(long) */
  @Override
  public Appointment getAppointmentById(long id) {
    return dao.find(id);
  }

  /** @see AppointmentService#createAppointment(Patient, Staff, String, String, LocalDateTime, LocalDateTime) */
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

  /** @see AppointmentService#delayAppointment(Appointment, LocalDateTime, LocalDateTime) */
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

  /** @see AppointmentService#cancelAppointment(Appointment) */
  @Override
  public void cancelAppointment(Appointment appointment) {
    Instant instant = Instant.now();
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    appointment.cancel();

    dao.update(appointment);
  }

  /** @see AppointmentService#finishAppointment(Appointment, LocalDateTime, int) */
  @Override
  public void finishAppointment(Appointment appointment, LocalDateTime finished, int delay) {
    Instant instant = Instant.now();
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    appointment.setFinished(Timestamp.valueOf(finished));
    appointment.setDelay(delay);
    appointment.finish();

    dao.update(appointment);
  }

  /** @see AppointmentService#deleteAppointment(Appointment) */
  @Override
  public void deleteAppointment(Appointment appointment) {
    dao.delete(appointment.getId());
  }

  /** @see AppointmentService#editAppointment(Appointment) */
  @Override
  public void editAppointment(Appointment appointment) {
    Instant instant = Instant.now();
    appointment.setChanged(new Timestamp(instant.toEpochMilli()));
    dao.update(appointment);
  }
}
