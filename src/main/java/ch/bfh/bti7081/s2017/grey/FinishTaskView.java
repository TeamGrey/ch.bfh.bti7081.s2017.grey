package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Created by gabor on 06/06/17.
 */
public class FinishTaskView extends HorizontalLayout{
    private static final long serialVersionUID = 1L;
    private Label taskDesc;
    private CheckBox status;
    private Button finish;
    private Task task;

    public FinishTaskView(Task task) {
        super();
        this.task = task;
        status = new CheckBox("", task.isFinished());
        status.setReadOnly(true);
        addComponent(status);
        taskDesc = new Label(task.getName());
        addComponent(taskDesc);
        finish = new Button("Abschliessen");
        finish.addClickListener(event -> finishTask());
        addComponent(finish);
    }

    public void finishTask() {
        status.setValue(true);
    }
}
