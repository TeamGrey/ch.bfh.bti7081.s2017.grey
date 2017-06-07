package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

import java.util.List;

/**
 * Created by gabor on 07/06/17.
 */
public class FinishAppointmentModel {
    private AppointmentService appointmentService = new AppointmentServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    private Appointment appointment;
    private List<Task> tasks;
    private int delay;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
        appointment.setDelay(delay);
        appointmentService.editAppointment(appointment);
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
