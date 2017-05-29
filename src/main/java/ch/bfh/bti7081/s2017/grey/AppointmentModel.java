package ch.bfh.bti7081.s2017.grey;

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

    private Appointment appointment = new Appointment();
    private Staff staff;
    private Date start;
    private Date end;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        this.appointment.setStaff(this.staff);
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void saveAppointment() {
        this.appointmentService.createAppointment(this.appointment.getPatient(), this.appointment.getStaff(), this.appointment.getDescription(), this.appointment.getTitle(), this.appointment.getDate(), this.appointment.getEndDate());
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
                start.getActualMinimum(Calendar.DAY_OF_WEEK));
        setTimeToBeginningOfDay(start);
        this.start = start.getTime();
        Calendar end = getCalendarForNow();
        end.set(Calendar.DAY_OF_WEEK,
                end.getActualMaximum(Calendar.DAY_OF_WEEK));
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

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    private static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
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
