package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.util.TaskEditor;

/**
 * @author Ken
 */
public class TaskView extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private CustomLayout tasklayout = new CustomLayout("tasklayout");
	private Label taskDesc = null;
	private CheckBox checkbox = null;
	private HorizontalLayout taskRight = null;
	private int estimateTime = 0;
	private Label estimate = null;
	private Button addTime = null;
	private Button removeTime = null;
	private Button removeAppointment = null;
	private Task task = null;

	public TaskView(Task task){
		super();
		this.task = task;
		//Task left
		checkbox = new CheckBox("", false);
		checkbox.addValueChangeListener(event -> toggleStatus());
		tasklayout.addComponent(checkbox, "task-left");
		
		//Task center
		taskDesc = new Label("Task Name");
		tasklayout.addComponent(taskDesc, "task-center");
		
		//Task right
		taskRight = new HorizontalLayout();
		estimateTime = 20;
		estimate = new Label("5 min");
		taskRight.addComponent(estimate);
		addTime = new Button("+ 5 min");
		taskRight.addComponent(addTime);
		removeTime = new Button("- 5 min");
		taskRight.addComponent(removeTime);
		removeAppointment = new Button("x");
		taskRight.addComponent(removeAppointment);
		
		addTime.addClickListener(event -> addToEstimate(5));
		removeTime.addClickListener(event -> removeFromEstimate(5));
		removeAppointment.addClickListener(event -> removeTask());
		tasklayout.addComponent(taskRight, "task-right");
		
		//Task Layout
		tasklayout.setSizeFull();
		addComponent(tasklayout);
		setSizeFull();
	}

	public void setName(String labelName) {
		taskDesc.setValue(labelName);
	}
	
	public void setFinished(boolean status){
		checkbox.setValue(status);
	}
	
	private void toggleStatus(){
		TaskEditor.toggleStatus(task, checkbox.getValue());
	}
	
	//newones
	public void setEstimate(int time){
		estimateTime = time;
		setNewDuration();
	}
	
	public void addToEstimate(int time){
		estimateTime += time;
		setNewDuration();
	}
	
	public void removeFromEstimate(int time){
		estimateTime -= time;
		if(estimateTime < 5){
			Notification.show("Mindestens 5 min pro To-Do", Notification.Type.WARNING_MESSAGE);
			estimateTime += time;
		}
		else{
			setNewDuration();
		}
	}
	
	private void setNewDuration(){
		TaskEditor.setDuration(task, estimateTime);
		estimate.setValue(estimateTime + " min");
	}
	
	private void removeTask(){
		TaskEditor.removeTask(task);
		PatientTabsPresenter.updateTodoTab();
	}
}
