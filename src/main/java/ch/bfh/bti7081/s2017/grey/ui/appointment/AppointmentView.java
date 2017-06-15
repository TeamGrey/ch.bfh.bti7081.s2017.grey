package ch.bfh.bti7081.s2017.grey.ui.appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import java.util.Date;
import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public interface AppointmentView {

  interface AppointmentViewListener {

    /**
     * If the ui is in edit mode then the appointment will be sent to the next ui
     */
    void startClick();

    /**
     * A new appointment is generated and set to the model
     */
    void newClick();

    /**
     * The selected appointment is saved by the model
     */
    void saveClick();

    /**
     * The selected appointment is deleted by the model
     */
    void deleteClick();

    /**
     * The selected appointment is moved by the model
     *
     * @param start The new start date
     */
    void appointmentMove(Date start);

    /**
     * The selected appointment is resized by the model
     *
     * @param start The new start date
     * @param end The new end date
     */
    void appointmentReisize(Date start, Date end);

    /**
     * The date range is set to the month ui by the model
     */
    void monthViewSelect();

    /**
     * The date range is set to the week ui by the model
     */
    void weekViewSelect();

    /**
     * The date range is set to the day ui by the model
     */
    void dayViewSelect();

    /**
     * The date range is set to a specific date by the model
     *
     * @param date The specific date
     */
    void dateSelect(Date date);

    /**
     * The date range is set to a specific date range by the model
     *
     * @param start The specific start date
     * @param end The specific end date
     */
    void dateRangeSelect(Date start, Date end);

    /**
     * The selected appointment is changed by the model
     *
     * @param appointment The selected appointment
     */
    void appointmentSelect(Appointment appointment);

    /**
     * The current user is set by the model
     *
     * @param username The current user
     */
    void viewEntered(String username);
  }

  void addListener(AppointmentViewListener listener);

  void setPatients(List<Patient> patients);

  void setAppointment(Appointment appointment, boolean isEditMode);

  void setAppointmentList(List<Appointment> appointmentList);

  void setStartDate(Date startDate);

  void setEndDate(Date endDate);
}
