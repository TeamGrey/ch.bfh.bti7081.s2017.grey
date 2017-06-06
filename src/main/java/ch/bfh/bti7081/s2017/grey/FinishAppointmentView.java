package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import com.vaadin.ui.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gabor on 06/06/17.
 */
public class FinishAppointmentView extends VerticalLayout{
    private static final long serialVersionUID = 1L;
    private Label summary;
    private FinishAppointmentViewDelay delay;
    private Label openTasks;
    private List<FinishTaskView> taskViews;
    private Label notes;
    private TextArea notesArea;
    private Button finish;

    private Appointment appointment;
    private List<Task> tasks;

    public FinishAppointmentView(Appointment appointment) {
        super();
        this.appointment = appointment;
        this.tasks = appointment.getTasks();
        this.taskViews = new LinkedList<FinishTaskView>();
        summary = new Label("Zusammenfassung");
        addComponent(summary);
        delay = new FinishAppointmentViewDelay(appointment);
        addComponent(delay);
        openTasks = new Label("Offene Tasks");
        notes = new Label("Notizen");
        notesArea = new TextArea();
        notesArea.setValue(appointment.getProtocol());
        finish = new Button("Abschliessen");
        finish.addClickListener(event -> finishAppointment());
    }

    public void finishAppointment() {
        appointment.finish();
    }
}

