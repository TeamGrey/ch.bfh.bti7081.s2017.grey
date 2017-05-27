package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.util.TaskEditor;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
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
    private Appointment appointment = null;

    public TaskListView(Appointment appointment) {
        super();
        taskViews = new LinkedList<TaskView>();
        tasks = new LinkedList<Task>();
        this.appointment = appointment;
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
        TextField taskName = new TextField();
        taskName.setPlaceholder("Taskbeschreibung");
        content.addComponent(taskName);
        Button saveTask = new Button("Task erstellen");
        content.addComponent(saveTask);
        saveTask.addClickListener(event -> saveNewTask(taskName.getValue(), window));
        
        window.setContent(content);
  
        UI.getCurrent().addWindow(window);
    }
    
    private void saveNewTask(String taskName, Window window){
    	//TaskEditor.createTask(taskName, appointment);
    	window.close();
    }
}
