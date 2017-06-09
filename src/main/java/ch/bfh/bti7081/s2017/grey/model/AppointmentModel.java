package ch.bfh.bti7081.s2017.grey.model;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import com.vaadin.data.Binder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentModel {
    private AppointmentService appointmentService = new AppointmentServiceImpl();
    private PatientService patientService = new PatientServiceImpl();
    private StaffService staffService = new StaffServiceImpl();

    private Appointment appointment;
    private Staff staff;
    private Date start;

    private Date end;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        this.appointment.setStaff(this.staff);
    }

    public void setNewAppointment() {
        Appointment appointment = new Appointment(new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "", "", null, null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        this.setAppointment(appointment);
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void saveAppointment() {
        if(this.isEditMode()) {
            this.appointmentService.editAppointment(appointment);
        } else {
            this.appointmentService.createAppointment(this.appointment.getPatient(), this.appointment.getStaff(), this.appointment.getDescription(), this.appointment.getTitle(), this.appointment.getDate(), this.appointment.getEndDate());
        }
    }

    public void moveApppointment(Date start) {
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

    public void resizeAppointment(Date start, Date end) {
        this.appointment.setDate(start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        this.appointment.setEndDate(end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        this.saveAppointment();
    }

    public void deleteAppointment() {
        this.appointmentService.deleteAppointment(this.appointment);
        this.setNewAppointment();
    }

    public boolean isEditMode() {
        return this.appointment.getId() != 0;
    }

    public void setUser(String username) {
        this.staff = staffService.findStaffByLogin(username);
        this.appointment.setStaff(this.staff);
    }

    public List<Patient> getPatients() {
        return this.patientService.getAllPatients();
    }

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

    public void setMonthRange() {
        Calendar start = getCalendarForNow();
        start.set(Calendar.DAY_OF_MONTH,
                start.getActualMinimum(Calendar.DAY_OF_MONTH));
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = getCalendarForNow();
        end.set(Calendar.DAY_OF_MONTH,
                end.getActualMaximum(Calendar.DAY_OF_MONTH));
        setTimeToEndofDay(end);
        this.end = end.getTime();
    }

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
        setTimeToEndofDay(end);
        this.end = end.getTime();
    }

    public void setDayRange() {
        Calendar start = getCalendarForNow();
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = getCalendarForNow();
        setTimeToEndofDay(end);
        this.end = end.getTime();
    }

    public void setDate(Date date) {
        Calendar start = Calendar.getInstance(Locale.GERMANY);
        start.setTime(date);
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = Calendar.getInstance(Locale.GERMANY);
        end.setTime(date);
        setTimeToEndofDay(end);
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

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
