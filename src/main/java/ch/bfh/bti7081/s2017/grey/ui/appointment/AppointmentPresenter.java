package ch.bfh.bti7081.s2017.grey.ui.appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentPresenter implements AppointmentView.AppointmentViewListener {
    public interface AppointmentPresenterListener {
        void appointmentSelected(Appointment appointment);
    }
    private List<AppointmentPresenterListener> listeners = new ArrayList<>();
    public void addListener(AppointmentPresenterListener listener) {
        listeners.add(listener);
    }

    private AppointmentModel appointmentModel;
    private AppointmentView appointmentView;

    public AppointmentPresenter(AppointmentView appointmentView, AppointmentModel appointmentModel) {
        this.appointmentModel = appointmentModel;
        this.appointmentView = appointmentView;

        this.appointmentModel.setNewAppointment();

        this.appointmentView.addListener(this);
        this.appointmentView.setPatients(this.appointmentModel.getPatients());
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    /**
     * If the ui is in edit mode then the appointment will be sent to the next ui
     */
    @Override
    public void startClick() {
        if(this.appointmentModel.isEditMode()) {
            for (AppointmentPresenterListener listener: listeners) {
                listener.appointmentSelected(this.appointmentModel.getAppointment());
            }
        }
    }

    /**
     * A new appointment is generated and set to the model
     */
    @Override
    public void newClick() {
        this.appointmentModel.setNewAppointment();
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    /**
     * The selected appointment is saved by the model
     */
    @Override
    public void saveClick() {
        this.appointmentModel.saveAppointment();
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    /**
     * The selected appointment is deleted by the model
     */
    @Override
    public void deleteClick() {
        this.appointmentModel.deleteAppointment();
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    /**
     * The selected appointment is moved by the model
     * @param start The new start date
     */
    @Override
    public void appointmentMove(Date start) {
        this.appointmentModel.moveApppointment(start);
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    /**
     * The selected appointment is resized by the model
     * @param start The new start date
     * @param end The new end date
     */
    @Override
    public void appointmentReisize(Date start, Date end) {
        this.appointmentModel.resizeAppointment(start, end);
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    /**
     * The date range is set to the month ui by the model
     */
    @Override
    public void monthViewSelect() {
        this.appointmentModel.setMonthRange();
        this.updateDate();
    }

    /**
     * The date range is set to the week ui by the model
     */
    @Override
    public void weekViewSelect() {
        this.appointmentModel.setWeekRange();
        this.updateDate();
    }

    /**
     * The date range is set to the day ui by the model
     */
    @Override
    public void dayViewSelect() {
        this.appointmentModel.setDayRange();
        this.updateDate();
    }

    /**
     * The date range is set to a specific date by the model
     * @param date The specific date
     */
    @Override
    public void dateSelect(Date date) {
        this.appointmentModel.setDate(date);
        this.updateDate();
    }

    /**
     * The date range is set to a specific date range by the model
     * @param start The specific start date
     * @param end The specific end date
     */
    @Override
    public void dateRangeSelect(Date start, Date end) {
        this.appointmentModel.setStart(start);
        this.appointmentModel.setEnd(end);
        this.updateDate();
    }

    private void updateDate() {
        this.appointmentView.setStartDate(this.appointmentModel.getStart());
        this.appointmentView.setEndDate(this.appointmentModel.getEnd());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    /**
     * The selected appointment is changed by the model
     * @param appointment The selected appointment
     */
    @Override
    public void appointmentSelect(Appointment appointment) {
        this.appointmentModel.setAppointment(appointment);
        this.appointmentView.setAppointment(appointment, this.appointmentModel.isEditMode());
    }

    /**
     * The current user is set by the model
     * @param username The current user
     */
    @Override
    public void viewEntered(String username) {
        this.weekViewSelect();
        this.appointmentModel.setUser(username);
        this.appointmentModel.setNewAppointment();
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }
}
