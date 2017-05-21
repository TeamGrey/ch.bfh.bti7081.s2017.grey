package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gabor on 21/05/17.
 */
public class StateMachineTest {
    private Appointment appointment;

    @Before
    public void setup() {
        appointment = new Appointment();
    }

    @Test
    public void noneToCreateState() {
        assertEquals(AppointmentStatus.NONE, appointment.getStatus());
        appointment.create();
        assertEquals(AppointmentStatus.CREATED, appointment.getStatus());
    }

    @Test (expected = IllegalStateException.class)
    public void noneToDelayedState() {
        assertEquals(AppointmentStatus.NONE, appointment.getStatus());
        appointment.delay();
    }

    @Test (expected = IllegalStateException.class)
    public void noneToCanceleddState() {
        assertEquals(AppointmentStatus.NONE, appointment.getStatus());
        appointment.cancel();
    }

    @Test (expected = IllegalStateException.class)
    public void noneToFinishedState() {
        assertEquals(AppointmentStatus.NONE, appointment.getStatus());
        appointment.finish();
    }

    // Only test create to create as the other 3 operations are tested in daoTest
    @Test(expected=IllegalStateException.class)
    public void createToCreateState() {
        appointment.create();
        assertEquals(AppointmentStatus.CREATED, appointment.getStatus());
        appointment.create();
    }

    @Test (expected = IllegalStateException.class)
    public void delayToCreateState() {
        appointment.create();
        appointment.delay();
        assertEquals(AppointmentStatus.DELAYED, appointment.getStatus());
        appointment.create();
    }

    @Test (expected = IllegalStateException.class)
    public void delayToDelayedState() {
        appointment.create();
        appointment.delay();
        assertEquals(AppointmentStatus.DELAYED, appointment.getStatus());
        appointment.delay();
    }

    @Test
    public void delayToCanceledState() {
        appointment.create();
        appointment.delay();
        assertEquals(AppointmentStatus.DELAYED, appointment.getStatus());
        appointment.cancel();
        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
    }

    @Test
    public void delayToFinishedState() {
        appointment.create();
        appointment.delay();
        assertEquals(AppointmentStatus.DELAYED, appointment.getStatus());
        appointment.finish();
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
    }

    @Test (expected = IllegalStateException.class)
    public void cancelToCreatedState() {
        appointment.create();
        appointment.cancel();
        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
        appointment.create();
    }

    @Test (expected = IllegalStateException.class)
    public void cancelToDelayedState() {
        appointment.create();
        appointment.cancel();
        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
        appointment.delay();
    }

    @Test (expected = IllegalStateException.class)
    public void cancelToCanceledState() {
        appointment.create();
        appointment.cancel();
        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
        appointment.cancel();
    }

    @Test
    public void cancelToFinishedState() {
        appointment.create();
        appointment.cancel();
        assertEquals(AppointmentStatus.CANCELED, appointment.getStatus());
        appointment.finish();
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
    }

    @Test (expected = IllegalStateException.class)
    public void finishToCreatedState() {
        appointment.create();
        appointment.finish();
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
        appointment.create();
    }

    @Test (expected = IllegalStateException.class)
    public void finishToDelayedState() {
        appointment.create();
        appointment.finish();
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
        appointment.delay();
    }

    @Test (expected = IllegalStateException.class)
    public void finishToCanceledState() {
        appointment.create();
        appointment.finish();
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
        appointment.cancel();
    }

    @Test (expected = IllegalStateException.class)
    public void finishToFinishedState() {
        appointment.create();
        appointment.finish();
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());
        appointment.finish();
    }
}
