package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ken
 */
public class TaskListView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private List<TaskView> taskViews;
    private static List<Task> tasks;
    private Button newTask = null;

    public TaskListView() {
        super();
        taskViews = new LinkedList<TaskView>();
        tasks = new LinkedList<Task>();
    }

    public TaskView addTask(Task task) {
        TaskView taskView = new TaskView(task);
        taskView.setSizeFull();
        taskView.setName(task.getName());
        taskView.setDrugs(task.getDrugs());
        taskView.setEstimate(task.getDuration());
        taskView.setFinished(task.isFinished());
        taskViews.add(taskView);
        tasks.add(task);
        addComponent(taskView);
        return taskView;
    }
    
    public void addNewTaskButton(){
    	newTask = new Button("Neuer Task");
    	newTask.addStyleName("newTaskButton");
    	addComponent(newTask);
    	newTask.addClickListener(event -> openForm());
    }
    
    private void openForm(){
    	Window window = new Window("Neuer Task");
        window.setWidth(300.0f, Unit.PIXELS);
        window.setModal(true);
        FormLayout content = new FormLayout();
        content.setMargin(true);
        Label tempLabel = new Label("Neu zu erstellenden Task hier...");
        content.addComponent(tempLabel);
        window.setContent(content);
  
        UI.getCurrent().addWindow(window);
    }
}
