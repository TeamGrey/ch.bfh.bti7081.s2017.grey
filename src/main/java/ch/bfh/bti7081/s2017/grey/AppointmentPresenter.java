package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentPresenter implements AppointmentView.AppontmentViewListener{
    AppointmentModel appointmentModel;
    AppointmentView appointmentView;

    public AppointmentPresenter(AppointmentView appointmentView, AppointmentModel appointmentModel) {
        this.appointmentModel = appointmentModel;
        this.appointmentView = appointmentView;

        this.appointmentView.addListener(this);
        this.appointmentModel.setView(this.appointmentView);
    }

    @Override
    public void saveClick() {

    }

    @Override
    public void editClick() {

    }
}
