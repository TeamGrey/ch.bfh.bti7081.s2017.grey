package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class TaskListView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private List<TaskView> taskViews;
    private List<Task> tasks;

    public TaskListView() {
        super();
        taskViews = new LinkedList<TaskView>();
        tasks = new LinkedList<Task>();
    }

    public TaskView addTask(Task task) {
        TaskView taskView = new TaskView();
        taskView.setName(task.getName());
        taskView.tempDrugs();
        taskViews.add(taskView);
        tasks.add(task);
        addComponent(taskView);
        return taskView;
    }
}
