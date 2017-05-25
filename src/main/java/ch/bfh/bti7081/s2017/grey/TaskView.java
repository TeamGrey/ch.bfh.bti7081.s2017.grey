package ch.bfh.bti7081.s2017.grey;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;

/**
 * @author Joel
 */
public class TaskView extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private CustomLayout tasklayout = new CustomLayout("tasklayout");
	private Label taskDesc = null;
	private DrugListView drugss = null;
	private CheckBox checkbox = null;
	private TaskViewTime time = null;

	public TaskView(){
		super();
		checkbox = new CheckBox("", false);
		tasklayout.addComponent(checkbox, "task-left");
		taskDesc = new Label("Task Name");
		tasklayout.addComponent(taskDesc, "task-center-desc");
		drugss = new DrugListView();
		tasklayout.addComponent(drugss, "task-center-drugs");
		time = new TaskViewTime();
		tasklayout.addComponent(time, "task-right");
		tasklayout.setSizeFull();
		addComponent(tasklayout);
		setSizeFull();
	}

	public void setName(String labelName) {
		taskDesc.setValue(labelName);
	}
	
	public void tempDrugs(){
		List<DrugTaskAssociation> drugList = new ArrayList<DrugTaskAssociation>();
		DrugTaskAssociation testDrug = new DrugTaskAssociation();
		Drug drug = new Drug();
        drug.setName("TempDrug");
    	testDrug.setDrug(drug);
    	drugList.add(testDrug);
    	DrugTaskAssociation testDrug2 = new DrugTaskAssociation();
    	Drug drug2 = new Drug();
        drug2.setName("TempDrug2");
    	testDrug2.setDrug(drug2);
    	drugList.add(testDrug2);
    	
    	for (DrugTaskAssociation object: drugList) {
    		drugss.addDrug(object.getDrug());
    	}
	}
}
