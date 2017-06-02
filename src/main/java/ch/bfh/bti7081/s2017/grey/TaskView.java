package ch.bfh.bti7081.s2017.grey;

import java.util.List;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
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
	private TaskViewTime timeView = null;
	private Task task = null;

	public TaskView(Task task){
		super();
		this.task = task;
		checkbox = new CheckBox("", false);
		checkbox.addValueChangeListener(event -> toggleStatus());
		tasklayout.addComponent(checkbox, "task-left");
		taskDesc = new Label("Task Name");
		tasklayout.addComponent(taskDesc, "task-center");
		timeView = new TaskViewTime(this.task);
		tasklayout.addComponent(timeView, "task-right");
		tasklayout.setSizeFull();
		addComponent(tasklayout);
		setSizeFull();
	}

	public void setName(String labelName) {
		taskDesc.setValue(labelName);
	}
	
	public void setEstimate(int time){
		timeView.setEstimate(time);
	}
	
	public void setFinished(boolean status){
		checkbox.setValue(status);
	}
	
	private void toggleStatus(){
		TaskEditor.toggleStatus(task, checkbox.getValue());
	}
}
