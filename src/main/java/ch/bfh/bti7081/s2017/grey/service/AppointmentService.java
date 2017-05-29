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

    List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date);
    List<Appointment> findAppointmentsByStaffAndDateRange(Staff staff, LocalDateTime start, LocalDateTime end);

    void createAppointment(Patient patient, Staff staff, String description, String title, LocalDateTime date, LocalDateTime end);
    void createAppointmentDummyPatient(Staff staff, String description, String title, LocalDateTime date, LocalDateTime end);
}
