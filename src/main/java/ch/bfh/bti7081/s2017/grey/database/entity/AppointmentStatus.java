package ch.bfh.bti7081.s2017.grey.database.entity;

import ch.bfh.bti7081.s2017.grey.service.AppointmentStatusOperations;
import ch.bfh.bti7081.s2017.grey.service.impl.CanceledAso;
import ch.bfh.bti7081.s2017.grey.service.impl.CreatedAso;
import ch.bfh.bti7081.s2017.grey.service.impl.DelayedAso;
import ch.bfh.bti7081.s2017.grey.service.impl.FinishedAso;

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
        return operations.create(appointment);
    }

    @Override
    public AppointmentStatus delay(Appointment appointment) {
        return operations.delay(appointment);
    }

    @Override
    public AppointmentStatus cancel(Appointment appointment) {
        return operations.cancel(appointment);
    }

    @Override
    public AppointmentStatus finish(Appointment appointment) {
        return operations.finish(appointment);
    }
}
