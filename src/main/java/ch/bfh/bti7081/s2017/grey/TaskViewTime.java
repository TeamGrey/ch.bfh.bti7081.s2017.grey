package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.util.TaskEditor;

/**
 * @author Ken
 */
public class TaskViewTime extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private int estimateTime = 0;
	private Label estimate = null;
	private Button addTime = null;
	private Button removeTime = null;
	private Button removeAppointment = null;
	private Task task = null;


	public TaskViewTime(Task task){
		super();
		this.task = task;
		estimateTime = 20;
		estimate = new Label("5 min");
		addComponent(estimate);
		addTime = new Button("+ 5 min");
		addComponent(addTime);
		removeTime = new Button("- 5 min");
		addComponent(removeTime);
		removeAppointment = new Button("x");
		addComponent(removeAppointment);
		
		addTime.addClickListener(event -> addToEstimate(5));
		removeTime.addClickListener(event -> removeFromEstimate(5));
		removeAppointment.addClickListener(event -> removeTask());
	}
	
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
	}
}
