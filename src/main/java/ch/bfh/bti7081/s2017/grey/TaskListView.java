package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ken
 */
public class TaskListView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private List<TaskView> taskViews;
    private List<Task> tasks;
    private Button newTask = null;

    public TaskListView() {
        super();
        taskViews = new LinkedList<TaskView>();
        tasks = new LinkedList<Task>();
    }

    public TaskView addTask(Task task) {
        TaskView taskView = new TaskView();
        taskView.setSizeFull();
        taskView.setName(task.getName());
        taskView.setDrugs(task.getDrugs());
        taskViews.add(taskView);
        tasks.add(task);
        addComponent(taskView);
        return taskView;
    }
    
    public void addNewTaskButton(){
    	newTask = new Button("Neuer Task");
    	newTask.addStyleName("newTaskButton");
    	addComponent(newTask);
    }
}
