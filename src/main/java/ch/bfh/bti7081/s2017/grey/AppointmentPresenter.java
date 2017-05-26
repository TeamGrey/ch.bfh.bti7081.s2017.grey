package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import com.vaadin.server.VaadinSession;

import java.time.LocalDate;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentPresenter implements AppointmentView.AppontmentViewListener{
    private AppointmentModel appointmentModel;
    private AppointmentView appointmentView;

    public AppointmentPresenter(AppointmentView appointmentView, AppointmentModel appointmentModel) {
        this.appointmentModel = appointmentModel;
        this.appointmentView = appointmentView;

        this.appointmentView.addListener(this);
        this.appointmentView.setPatients(this.appointmentModel.getPatients());
        this.appointmentView.setAppointment(this.appointmentModel.getAppointment());
    }

    @Override
    public void saveClick() {
        this.appointmentModel.saveAppointment();
    }

    @Override
    public void editClick() {

    }

    @Override
    public void monthViewSelect() {
        this.appointmentModel.setMonthRange();
        this.appointmentView.setStartDate(this.appointmentModel.getStart());
        this.appointmentView.setEndDate(this.appointmentModel.getEnd());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    @Override
    public void weekViewSelect() {
        this.appointmentModel.setWeekRange();
        this.appointmentView.setStartDate(this.appointmentModel.getStart());
        this.appointmentView.setEndDate(this.appointmentModel.getEnd());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    @Override
    public void dayViewSelect() {
        this.appointmentModel.setDayRange();
        this.appointmentView.setStartDate(this.appointmentModel.getStart());
        this.appointmentView.setEndDate(this.appointmentModel.getEnd());
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }

    @Override
    public void appointmentSelect(Appointment appointment) {
        this.appointmentModel.setAppointment(appointment);
        this.appointmentView.setAppointment(appointment);
    }

    @Override
    public void viewEntered(String username) {
        this.weekViewSelect();
        this.appointmentModel.setUser(username);
        this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
    }
}
