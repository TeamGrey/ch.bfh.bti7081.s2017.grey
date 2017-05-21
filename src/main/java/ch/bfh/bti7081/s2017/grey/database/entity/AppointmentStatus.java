package ch.bfh.bti7081.s2017.grey.database.entity;

import ch.bfh.bti7081.s2017.grey.state.*;

/**
 * Created by gabor on 17/05/17.
 */
public enum  AppointmentStatus implements AppointmentStatusOperations{
    NONE(new NoneAso()),
    CREATED(new CreatedAso()),
    DELAYED(new DelayedAso()),
    CANCELED(new CanceledAso()),
    FINISHED(new FinishedAso());

    private final AppointmentStatusOperations operations;

    AppointmentStatus(AppointmentStatusOperations operations) {
        this.operations = operations;
    }

    @Override
    public AppointmentStatus create(Appointment appointment) throws IllegalStateException{
        return operations.create(appointment);
    }

    @Override
    public AppointmentStatus delay(Appointment appointment) throws IllegalStateException {
           return operations.delay(appointment);
    }

    @Override
    public AppointmentStatus cancel(Appointment appointment) throws IllegalStateException{
        return operations.cancel(appointment);
    }

    @Override
    public AppointmentStatus finish(Appointment appointment) throws IllegalStateException {
        return operations.finish(appointment);

    }
}
