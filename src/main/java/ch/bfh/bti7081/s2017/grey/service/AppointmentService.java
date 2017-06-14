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
    /**
     * Finds all appointment of one staff and a specific date
     *
     * @param staff Staff whose appointments are wanted
     * @param date Date of the appointments
     * @return List of appointments
     */
    List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date);
    /**
     * Finds all appointment of one staff and a date range
     *
     * @param staff Staff whose appointments are wanted
     * @param start Start of date range
     * @param end End of date range
     * @return List of appointments
     */
    List<Appointment> findAppointmentsByStaffAndDateRange(Staff staff, LocalDateTime start, LocalDateTime end);
    /**
     * Find an appointment by it's id
     *
     * @param id Id of the appointment
     * @return Appointment if found
     */
    Appointment getAppointmentById(long id);
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
    Appointment createAppointment(Patient patient, Staff staff, String description, String title, LocalDateTime date, LocalDateTime end);
    /**
     * Delay an appointment
     *
     * @param appointment Appointment to be delayed
     * @param newDate New start date of the appointment
     * @param newEnd New end date of the appointment
     * @return Delayed appointment
     */
    Appointment delayAppointment(Appointment appointment, LocalDateTime newDate, LocalDateTime newEnd);
    /**
     * Cancel an appointment
     *
     * @param appointment Appointment to be canceled
     */
    void cancelAppointment(Appointment appointment);
    /**
     * Finish an appointment
     *
     * @param appointment Appointment to be finished
     * @param finished Time when it was finished
     * @param delay Delay with which it was finished
     */
    void finishAppointment(Appointment appointment, LocalDateTime finished, int delay);
    /**
     * Deletes an appointment
     *
     * @param appointment Appointment to be deleted
     */
    void deleteAppointment(Appointment appointment);
    /**
     * Updates an appointment
     *
     * @param appointment Appointment to be updated
     */
    void editAppointment(Appointment appointment);
}
