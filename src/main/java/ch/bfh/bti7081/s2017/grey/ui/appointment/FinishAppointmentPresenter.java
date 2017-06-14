package ch.bfh.bti7081.s2017.grey.ui.appointment;

import com.vaadin.ui.Notification;

/**
 * Created by gabor on 07/06/17.
 */
public class FinishAppointmentPresenter implements FinishAppointmentView.FinishAppointmentViewListener{
    private FinishAppointmentModel finishAppointmentModel;
    private FinishAppointmentView finishAppointmentView;

    public FinishAppointmentPresenter(FinishAppointmentModel finishAppointmentModel, FinishAppointmentView finishAppointmentView) {
        this.finishAppointmentModel = finishAppointmentModel;
        this.finishAppointmentView = finishAppointmentView;

        this.finishAppointmentView.addListener(this);
        this.finishAppointmentView.setAppointment(finishAppointmentModel.getAppointment(1));
    }

    @Override
    public void addDelayClick(int time) {
        int delayMinutes = finishAppointmentModel.getDelay();
        delayMinutes += time;
        updateDelay(delayMinutes);
    }

    @Override
    public void subtractDelayClick(int time) {
        int delayMinutes = finishAppointmentModel.getDelay();
        if (delayMinutes >= 5) {
            delayMinutes -= time;
            updateDelay(delayMinutes);
        } else {
            Notification.show("Mindestens 0 min Verspätung", Notification.Type.WARNING_MESSAGE);
        }
    }

    @Override
    public void finishClick() {
        finishAppointmentModel.finish();
    }

    private void updateDelay(int delay) {
        finishAppointmentModel.setDelay(delay);
        finishAppointmentView.setDelay(delay);
    }

    @Override
    public void viewEntered(long AppointmentId) {
        finishAppointmentView.setAppointment(finishAppointmentModel.getAppointment(AppointmentId));
    }
}
