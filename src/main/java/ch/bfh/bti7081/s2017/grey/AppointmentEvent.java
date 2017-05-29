package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import com.vaadin.v7.ui.components.calendar.event.CalendarEvent;

import java.util.Date;

/**
 * Created by Nic on 26.05.17.
 */
public class AppointmentEvent implements CalendarEvent {
    private Appointment appointment;
    private Date start;
    private Date end;
    private String caption;
    private String description;
    private String styleName;
    private boolean allDay;

    public AppointmentEvent(Appointment appointment, Date start, Date end, String caption, String description) {
        this.appointment = appointment;
        this.start = start;
        this.end = end;
        this.caption = caption;
        this.description = description;
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
}
