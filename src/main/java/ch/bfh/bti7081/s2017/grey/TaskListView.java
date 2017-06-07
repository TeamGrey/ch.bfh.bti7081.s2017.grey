package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.util.TaskEditor;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author Ken
 */
public class TaskListView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
    private Button newTask = null;
    private Appointment appointment = null;

    public TaskListView(Appointment appointment) {
        super();
        this.appointment = appointment;
    }

    public void addTask(Task task) {
        TodoPresenter taskView = new TodoPresenter(task);
        addComponent((Component) taskView.getView());
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
    	TaskEditor.createTask(taskName, appointment);
    	PatientTabsPresenter.updateTodoTab();
    	window.close();
    }
}
