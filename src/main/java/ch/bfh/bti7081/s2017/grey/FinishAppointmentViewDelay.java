package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

/**
 * Created by gabor on 06/06/17.
 */
public class FinishAppointmentViewDelay extends HorizontalLayout {
    private static final long serialVersionUID = 1L;
    private int delayMinutes = 0;
    private Label delayText;
    private Label delay;
    private Button addDelay;
    private Button subtractDelay;
    private Appointment appointment;

    public FinishAppointmentViewDelay(Appointment appointment) {
        this.appointment = appointment;
        delayText = new Label("Verspätung:");
        addComponent(delayText);
        delay = new Label("5 min");
        addComponent(delay);
        addDelay = new Button("+ 5 min");
        addComponent(addDelay);
        subtractDelay = new Button("- 5 min");
        addComponent(subtractDelay);

        addDelay.addClickListener(event -> addToDelay(5));
        subtractDelay.addClickListener(event -> subtractFromDelay(5));
    }

    public void addToDelay(int time) {
        delayMinutes += time;
        updateDelayLabel();
    }

    public void subtractFromDelay(int time) {
        if (delayMinutes >= 5) {
            delayMinutes -= time;
            updateDelayLabel();
        } else {
            Notification.show("Mindestens 0 min Verspätung", Notification.Type.WARNING_MESSAGE);
        }
    }

    public void updateDelayLabel() {
        delay.setValue(delayMinutes + " min");
    }
}
