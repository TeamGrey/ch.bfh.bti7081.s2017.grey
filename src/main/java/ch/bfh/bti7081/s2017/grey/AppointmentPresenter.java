package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import com.vaadin.server.VaadinSession;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentPresenter implements AppointmentView.AppontmentViewListener{
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

    @Override
    public void newClick() {
        this.appointmentModel.setNewAppointment();
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    @Override
    public void saveClick() {
        this.appointmentModel.saveAppointment();
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    @Override
    public void deleteClick() {
        this.appointmentModel.deleteAppointment();
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    @Override
    public void appointmentMove(Date start) {
        this.appointmentModel.moveApppointment(start);
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    @Override
    public void appointmentReisize(Date start, Date end) {
        this.appointmentModel.resizeAppointment(start, end);
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    }

    @Override
    public void monthViewSelect() {
        this.appointmentModel.setMonthRange();
        this.updateDate();
    }

    @Override
    public void weekViewSelect() {
        this.appointmentModel.setWeekRange();
        this.updateDate();
    }

    @Override
    public void dayViewSelect() {
        this.appointmentModel.setDayRange();
        this.updateDate();
    }

    @Override
    public void dateSelect(Date date) {
        this.appointmentModel.setDate(date);
        this.updateDate();
    }

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

    @Override
    public void appointmentSelect(Appointment appointment) {
        this.appointmentModel.setAppointment(appointment);
        this.appointmentView.setAppointment(appointment, this.appointmentModel.isEditMode());
    }

    @Override
    public void viewEntered(String username) {
        this.weekViewSelect();
        this.appointmentModel.setUser(username);
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }
}
