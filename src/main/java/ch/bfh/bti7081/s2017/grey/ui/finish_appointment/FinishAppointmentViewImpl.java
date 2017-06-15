package ch.bfh.bti7081.s2017.grey.ui.finish_appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.ui.Design;
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

    private List<FinishAppointmentViewListener> listeners = new ArrayList<>();
    private Binder<Appointment> binder = new Binder<>(Appointment.class);
    private List<Binder<Task>> taskBinders = new ArrayList<>();

    private Button addDelay = new Button();
    private Button subtractDelay = new Button();
    private Button finish = new Button();

    private TextArea protocol = new TextArea();

    private Label summary = new Label("Zusammenfassung");
    private Label delay = new Label("Verspätung");
    private Label delayAmount = new Label("0 Min");
    private Label openTasks = new Label("Tasks");

    private VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout delayLayout = new HorizontalLayout();

    public FinishAppointmentViewImpl() {
        addDelay.setCaption("+ 5 Min");
        subtractDelay.setCaption("- 5 Min");
        finish.setCaption("Abschliessen");

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

    }

    @Override
    public void addListener(FinishAppointmentViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setAppointment(Appointment appointment) {
        layout.addComponent(summary);
        setDelay(appointment.getDelay());
        delayLayout.addComponents(delay, delayAmount, addDelay, subtractDelay);
        layout.addComponent(delayLayout);
        layout.addComponent(openTasks);
        binder.forField(protocol).bind(Appointment::getProtocol, Appointment::setProtocol);
        binder.setBean(appointment);
        for (Task task : appointment.getTasks()) {
            Binder<Task> taskBinder = new Binder<>(Task.class);
            taskBinder.setBean(task);
            HorizontalLayout taskLayout = new HorizontalLayout();
            Label description = new Label(task.getName());
            CheckBox finished = new CheckBox();
            taskBinder.forField(finished).bind(Task::isFinished, Task::toggleFinished);
            taskBinders.add(taskBinder);
            taskLayout.addComponents(description, finished);
            layout.addComponent(taskLayout);
        }
        layout.addComponent(protocol);
        layout.addComponent(finish);
    }

    @Override
    public void setDelay(int delayMinutes) {
        delayAmount.setValue(delayMinutes + " Min");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        for (FinishAppointmentViewListener listener : listeners) {
            listener.viewEntered();
        }

        Design design = new Design();
        this.addComponent(design.insertContent(layout,false, false));
    }
}