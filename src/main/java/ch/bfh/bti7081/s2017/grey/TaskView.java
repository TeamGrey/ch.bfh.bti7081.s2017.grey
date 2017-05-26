package ch.bfh.bti7081.s2017.grey;

import java.util.List;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;

/**
 * @author Ken
 */
public class TaskView extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private CustomLayout tasklayout = new CustomLayout("tasklayout");
	private Label taskDesc = null;
	private DrugListView drugs = null;
	private CheckBox checkbox = null;
	private TaskViewTime time = null;

	public TaskView(){
		super();
		checkbox = new CheckBox("", false);
		tasklayout.addComponent(checkbox, "task-left");
		taskDesc = new Label("Task Name");
		tasklayout.addComponent(taskDesc, "task-center-desc");
		drugs = new DrugListView();
		tasklayout.addComponent(drugs, "task-center-drugs");
		time = new TaskViewTime();
		tasklayout.addComponent(time, "task-right");
		tasklayout.setSizeFull();
		addComponent(tasklayout);
		setSizeFull();
	}

	public void setName(String labelName) {
		taskDesc.setValue(labelName);
	}
	
	public void setDrugs(List<DrugTaskAssociation> drugList){
    	for (DrugTaskAssociation object: drugList) {
    		drugs.addDrug(object.getDrug());
    	}
	}
}
