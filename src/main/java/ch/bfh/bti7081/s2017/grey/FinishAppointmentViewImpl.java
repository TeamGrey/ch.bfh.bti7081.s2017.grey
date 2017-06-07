package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabor on 07/06/17.
 */
public class FinishAppointmentViewImpl extends VerticalLayout implements FinishAppointmentView, View{

    private static final long serialVersionUID = 1L;
    public static final String NAME = "FinishAppointmentViewImpl";

    private List<FinishAppointmentViewListener> listeners = new ArrayList<FinishAppointmentViewListener>();
    private Binder<Appointment> binder = new Binder<>(Appointment.class);

    private Button addDelay = new Button();
    private Button subtractDelay = new Button();
    private Button finish = new Button();

    private TextArea protocol = new TextArea();

    private Label summary = new Label("Abschliessen");
    private Label delay = new Label("Verspätung");
    private Label delayAmount = new Label("0 Min");
    private Label openTasks = new Label("Offene Tasks");

    private HorizontalLayout delayLayout = new HorizontalLayout();
    private List<HorizontalLayout> tasksLayout = new ArrayList<HorizontalLayout>();

    public FinishAppointmentViewImpl() {
        addDelay.setCaption("+ 5 Min");
        subtractDelay.setCaption("- 5 Min");
        finish.setCaption("Zusammenfassung");

        protocol.setCaption("Protokoll");

        addDelay.addClickListener(e -> {
            for(FinishAppointmentViewListener listener: listeners) {
                listener.addDelayClick(5);
            }
        });
        subtractDelay.addClickListener(e -> {
            for(FinishAppointmentViewListener listener: listeners) {
                listener.subtractDelayClick(5);
            }
        });
        finish.addClickListener(e -> {
            for(FinishAppointmentViewListener listener: listeners) {
                listener.finishClick();
            }
        });
        addComponent(summary);
        delayLayout.addComponents(delay, delayAmount, addDelay, subtractDelay);
        addComponent(delayLayout);

    }

    @Override
    public void addListener(FinishAppointmentViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setAppointment(Appointment appointment) {
        binder.setBean(appointment);
    }

    @Override
    public void setTasks(List<Task> tasks) {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public void setDelay(int delayMinutes) {
        delayAmount.setValue(delayMinutes + " Min");
    }
}
