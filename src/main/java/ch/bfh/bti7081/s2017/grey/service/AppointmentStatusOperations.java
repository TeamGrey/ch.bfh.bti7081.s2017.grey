package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.util.UnsupportedStatusTransitionException;

/**
 * Created by gabor on 17/05/17.
 */
public interface AppointmentStatusOperations {
    public AppointmentStatus create(Appointment appointment) throws UnsupportedStatusTransitionException;
    public AppointmentStatus delay(Appointment appointment) throws UnsupportedStatusTransitionException;
    public AppointmentStatus cancel(Appointment appointment) throws UnsupportedStatusTransitionException;
    public AppointmentStatus finish(Appointment appointment) throws UnsupportedStatusTransitionException;
}
