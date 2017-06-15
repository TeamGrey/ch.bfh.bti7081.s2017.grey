package ch.bfh.bti7081.s2017.grey.ui.finish_appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;

/**
 * Created by gabor on 07/06/17.
 */
public class FinishAppointmentModel {

  private AppointmentService appointmentService = new AppointmentServiceImpl(
      EntityManagerSingleton.getInstance());

  private Appointment appointment;
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
    return this.appointment;
  }

  public void setAppointment(Appointment appointment) {
    this.appointment = appointment;
  }

  public void finish() {
    appointment.finish();
    appointmentService.editAppointment(appointment);
  }
}
