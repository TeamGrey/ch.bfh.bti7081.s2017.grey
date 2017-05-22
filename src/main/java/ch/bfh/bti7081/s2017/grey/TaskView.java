package ch.bfh.bti7081.s2017.grey;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;

/**
 * @author Joel
 */
public class TaskView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private Label name = null;
	private DrugListView drugss = null;

	public TaskView(){
		super();
		name = new Label("Task Name");
		addComponent(name);
		drugss = new DrugListView();
		addComponent(drugss);
	}

	public void setName(String labelName) {
		name.setValue(labelName);
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
