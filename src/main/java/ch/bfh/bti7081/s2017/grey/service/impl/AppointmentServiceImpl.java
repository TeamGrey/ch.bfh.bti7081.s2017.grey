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

/**
 * @Author Quentin
 */
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentDao dao;

    public AppointmentServiceImpl(){
        dao = new AppointmentDao();
    }

    @Override
    public List<Appointment> findAppointmentsByStaffAndDateRange(Staff staff, LocalDateTime start, LocalDateTime end) {
        return dao.findAppointmentsForStaffAndDateRange(staff, start, end);
    }

    @Override
    public List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date) {
        return dao.findAppointmentsForStaffAndDay(staff, date);
    }
    
    @Override
    public Appointment getAppointmentById(long id){
        return dao.find(id);
    }

    @Override
    public Appointment createAppointment(Patient patient, Staff staff, String description, String title, LocalDateTime date, LocalDateTime end) {
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

    @Override
    public Appointment delayAppointment(Appointment appointment, LocalDateTime newDate, LocalDateTime newEnd) {
        Instant instant = Instant.now();
        appointment.setDate(newDate);
        appointment.setEndDate(newEnd);
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.delay();

        return dao.update(appointment);
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        Instant instant = Instant.now();
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.cancel();

        dao.update(appointment);
    }

    @Override
    public void finishAppointment(Appointment appointment, LocalDateTime finished, int delay) {
        Instant instant = Instant.now();
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.setFinished(Timestamp.valueOf(finished));
        appointment.setDelay(delay);
        appointment.finish();

        dao.update(appointment);
    }

    @Override
    public void createAppointmentDummyPatient(Staff staff, String description, String title, LocalDateTime date, LocalDateTime end) {
        PatientService patientService = new PatientServiceImpl();
        Patient patient = patientService.getPatientByName("Test", "Test");
        createAppointment(patient, staff, description, title, date, end);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        dao.delete(appointment.getId());
    }

    @Override
    public void editAppointment(Appointment appointment) {
        Instant instant = Instant.now();
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        dao.update(appointment);
    }
}
