package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.service.AppointmentStatusOperations;
import ch.bfh.bti7081.s2017.grey.util.UnsupportedStatusTransitionException;

/**
 * Created by gabor on 17/05/17.
 */
public class DelayedAso implements AppointmentStatusOperations {
    @Override
    public AppointmentStatus create(Appointment appointment) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("create", appointment.getStatus());
    }

    @Override
    public AppointmentStatus delay(Appointment appointment) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("delay", appointment.getStatus());
    }

    @Override
    public AppointmentStatus cancel(Appointment appointment) {
        return null;
    }

    @Override
    public AppointmentStatus finish(Appointment appointment) {
        return null;
    }
}
