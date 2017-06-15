package ch.bfh.bti7081.s2017.grey.state;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;

/**
 * Created by gabor on 17/05/17.
 */
public class CanceledAso implements AppointmentStatusOperations {

  @Override
  public AppointmentStatus create(Appointment appointment) throws IllegalStateException {
    throw new IllegalStateException(
        "Cant use operation create with status " + appointment.getStatus().toString());
  }

  @Override
  public AppointmentStatus delay(Appointment appointment) {
    throw new IllegalStateException(
        "Cant use operation delay with status " + appointment.getStatus().toString());
  }

  @Override
  public AppointmentStatus cancel(Appointment appointment) throws IllegalStateException {
    throw new IllegalStateException(
        "Cant use operation cancel with status " + appointment.getStatus().toString());
  }

  @Override
  public AppointmentStatus finish(Appointment appointment) {
    return AppointmentStatus.FINISHED;
  }
}
