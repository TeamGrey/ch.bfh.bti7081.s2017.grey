package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Quentin
 */
public interface AppointmentService {
    public List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date);
    public Appointment createAppointment(Patient patient, Staff staff, String description, String title, LocalDateTime date, LocalDateTime end);
    public Appointment delayAppointment(Appointment appointment, LocalDateTime newDate, LocalDateTime newEnd);
    public void cancelAppointment(Appointment appointment);
    public void finishAppointment(Appointment appointment, LocalDateTime finished, int delay);
    public void createAppointmentDummyPatient(Staff staff, String description, String title, LocalDateTime date, LocalDateTime end);
    public void deleteAppointment(Appointment appointment);
}
