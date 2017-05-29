package ch.bfh.bti7081.s2017.grey;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;

/**
 * @author Ken
 */
public class TaskViewTime extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private Label estimate = null;
	private Button addTime = null;
	private Button removeTime = null;


	public TaskViewTime(){
		super();
		estimate = new Label("20 min");
		addComponent(estimate);
		addTime = new Button("+ 5 min");
		addComponent(addTime);
		removeTime = new Button("- 5 min");
		addComponent(removeTime);
	}
}
