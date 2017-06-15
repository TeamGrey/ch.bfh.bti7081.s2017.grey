package ch.bfh.bti7081.s2017.grey.ui.appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import com.vaadin.v7.ui.components.calendar.event.CalendarEvent;

import java.util.Date;

/**
 * Created by Nic on 26.05.17.
 * This class adds an Appointment to the CalendarEvent
 */
@SuppressWarnings("deprecation")
public class AppointmentEvent implements CalendarEvent {
    private Appointment appointment;
    private Date start;
    private Date end;
    private String caption;

    private String description;
    private String styleName;
    private boolean allDay;

    public AppointmentEvent(Appointment appointment, Date start, Date end, String caption, String description, String styleName) {
        this.appointment = appointment;
        this.start = start;
        this.end = end;
        this.caption = caption;
        this.description = description;
        this.styleName = styleName;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getStyleName() {
        return styleName;
    }

    @Override
    public boolean isAllDay() {
        return allDay;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
}
