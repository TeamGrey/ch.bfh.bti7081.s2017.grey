package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;

/**
 * Created by gabor on 17/05/17.
 */
public interface AppointmentStatusOperations {
    public AppointmentStatus create(Appointment appointment);
    public AppointmentStatus delay(Appointment appointment);
    public AppointmentStatus cancel(Appointment appointment);
    public AppointmentStatus finish(Appointment appointment);
}
