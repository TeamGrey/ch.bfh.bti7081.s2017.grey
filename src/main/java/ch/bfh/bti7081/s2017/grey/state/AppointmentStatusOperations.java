package ch.bfh.bti7081.s2017.grey.state;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;

/**
 * Created by gabor on 17/05/17.
 */
public interface AppointmentStatusOperations {

  AppointmentStatus create(Appointment appointment) throws IllegalStateException;

  AppointmentStatus delay(Appointment appointment) throws IllegalStateException;

  AppointmentStatus cancel(Appointment appointment) throws IllegalStateException;

  AppointmentStatus finish(Appointment appointment) throws IllegalStateException;
}
