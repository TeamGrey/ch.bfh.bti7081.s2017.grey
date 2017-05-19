package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;

/**
 * Created by gabor on 17/05/17.
 */
public interface AppointmentStatusOperations {
    public AppointmentStatus create(Appointment appointment) throws IllegalStateException;
    public AppointmentStatus delay(Appointment appointment) throws IllegalStateException;
    public AppointmentStatus cancel(Appointment appointment) throws IllegalStateException;
    public AppointmentStatus finish(Appointment appointment) throws IllegalStateException;
}
