package ch.bfh.bti7081.s2017.grey.database.entity;

import ch.bfh.bti7081.s2017.grey.service.AppointmentStatusOperations;
import ch.bfh.bti7081.s2017.grey.service.impl.CanceledAso;
import ch.bfh.bti7081.s2017.grey.service.impl.CreatedAso;
import ch.bfh.bti7081.s2017.grey.service.impl.DelayedAso;
import ch.bfh.bti7081.s2017.grey.service.impl.FinishedAso;
import ch.bfh.bti7081.s2017.grey.util.UnsupportedStatusTransitionException;

/**
 * Created by gabor on 17/05/17.
 */
public enum  AppointmentStatus implements AppointmentStatusOperations{
    CREATED(new CreatedAso()),
    DELAYED(new DelayedAso()),
    CANCELED(new CanceledAso()),
    FINISHED(new FinishedAso());

    private final AppointmentStatusOperations operations;

    AppointmentStatus(AppointmentStatusOperations operations) {
        this.operations = operations;
    }

    @Override
    public AppointmentStatus create(Appointment appointment) {
        AppointmentStatus status = appointment.getStatus();
        try {
            status = operations.create(appointment);
        }
        catch (UnsupportedStatusTransitionException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public AppointmentStatus delay(Appointment appointment) {
        AppointmentStatus status = appointment.getStatus();
        try {
            status = operations.delay(appointment);
        }
        catch (UnsupportedStatusTransitionException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public AppointmentStatus cancel(Appointment appointment) {
        AppointmentStatus status = appointment.getStatus();
        try {
            status = operations.cancel(appointment);
        }
        catch (UnsupportedStatusTransitionException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public AppointmentStatus finish(Appointment appointment) {
        AppointmentStatus status = appointment.getStatus();
        try {
            status = operations.finish(appointment);
        }
        catch (UnsupportedStatusTransitionException e) {
            e.printStackTrace();
        }
        return status;
    }
}
