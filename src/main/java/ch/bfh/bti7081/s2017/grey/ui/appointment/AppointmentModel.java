package ch.bfh.bti7081.s2017.grey.ui.appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.*;
import javax.persistence.EntityManager;

/**
 * Created by Nic on 15.05.17.
 * Model for the Appointment View
 */
public class AppointmentModel {
    private AppointmentService appointmentService = new AppointmentServiceImpl(
        EntityManagerSingleton.getInstance());
    private EntityManager em = EntityManagerSingleton.getInstance();
    private PatientService patientService = new PatientServiceImpl(em);
    private StaffService staffService = new StaffServiceImpl(em);

    private Appointment appointment;
    private Staff staff;
    private Date start;

    private Date end;

    /**
     * Set which appointment is selected
     * @param appointment selected appointment
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        this.appointment.setStaff(this.staff);
    }

    /**
     * Set a new appointment as selected
     */
    public void setNewAppointment() {
        Appointment appointment = new Appointment(new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "", "", null, null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        this.setAppointment(appointment);
    }

    /**
     * Get the selected appointment
     * @return the selected appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Save the currently selected appointment
     */
    public void saveAppointment() {
        if(this.isEditMode()) {
            this.appointmentService.editAppointment(appointment);
        } else {
            this.appointmentService.createAppointment(this.appointment.getPatient(), this.appointment.getStaff(), this.appointment.getDescription(), this.appointment.getTitle(), this.appointment.getDate(), this.appointment.getEndDate());
        }
    }

    /**
     * Move the selected appointment to an other start date
     * @param start the new start date
     */
    public void moveAppointment(Date start) {
        long timeDifference = Date.from(appointment.getEndDate().atZone(ZoneId.systemDefault()).toInstant()).getTime() - Date.from(appointment.getDate().atZone(ZoneId.systemDefault()).toInstant()).getTime();
        int seconds = (int)(timeDifference / 1000);

        appointment.setDate(start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        Calendar calendar = Calendar.getInstance(Locale.GERMANY);
        calendar.setTime(Date.from(appointment.getDate().atZone(ZoneId.systemDefault()).toInstant()));
        calendar.add(Calendar.SECOND, seconds);
        Date end = calendar.getTime();
        appointment.setEndDate(end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        this.saveAppointment();
    }

    /**
     * Resizes the selected appointment
     * @param start the new start date
     * @param end the new end date
     */
    public void resizeAppointment(Date start, Date end) {
        this.appointment.setDate(start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        this.appointment.setEndDate(end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        this.saveAppointment();
    }

    /**
     * Deletes the selected appointment
     */
    public void deleteAppointment() {
        this.appointmentService.deleteAppointment(this.appointment);
        this.setNewAppointment();
    }

    /**
     * Tells if the ui is in edit mode.
     * The ui is considered in edit mode if the appointment is already in the database.
     * @return true if the ui is in edit mode
     */
    public boolean isEditMode() {
        return this.appointment.getId() != 0;
    }

    /**
     * Set the user who is using the appointment ui
     * @param username the username that is used to set the user
     */
    public void setUser(String username) {
        this.staff = staffService.findStaffByLogin(username);
        this.appointment.setStaff(this.staff);
    }

    /**
     * Get all patients
     * @return list of all patients
     */
    public List<Patient> getPatients() {
        return this.patientService.getAllPatients();
    }

    /**
     * Get the list of appointments that are form the currently set user and between the set time range
     * @return the list of appointments
     */
    public List<Appointment> getAppointmentList() {
        return this.appointmentService.findAppointmentsByStaffAndDateRange(this.staff, this.start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), this.end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Set the date range to the current month
     */
    public void setMonthRange() {
        Calendar start = getCalendarForNow();
        start.set(Calendar.DAY_OF_MONTH,
                start.getActualMinimum(Calendar.DAY_OF_MONTH));
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = getCalendarForNow();
        end.set(Calendar.DAY_OF_MONTH,
                end.getActualMaximum(Calendar.DAY_OF_MONTH));
        setTimeToEndOfDay(end);
        this.end = end.getTime();
    }

    /**
     * Set the date range to the current week
     */
    public void setWeekRange() {
        Calendar start = getCalendarForNow();
        start.set(Calendar.DAY_OF_WEEK,
                start.getFirstDayOfWeek());
        Date s = start.getTime();
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = getCalendarForNow();
        end.set(Calendar.DAY_OF_WEEK,
                1);
        setTimeToEndOfDay(end);
        this.end = end.getTime();
    }

    /**
     * Set the date range to the current day
     */
    public void setDayRange() {
        Calendar start = getCalendarForNow();
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = getCalendarForNow();
        setTimeToEndOfDay(end);
        this.end = end.getTime();
    }

    /**
     * Set the date range to a specific date
     * @param date The specific date to be set
     */
    public void setDate(Date date) {
        Calendar start = Calendar.getInstance(Locale.GERMANY);
        start.setTime(date);
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = Calendar.getInstance(Locale.GERMANY);
        end.setTime(date);
        setTimeToEndOfDay(end);
        this.end = end.getTime();
    }

    private static Calendar getCalendarForNow() {
        Calendar calendar = Calendar.getInstance(Locale.GERMANY);
        calendar.setTime(new Date());
        return calendar;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
