package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Joel
 */
public class DrugView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private Label name = null;

	public DrugView(){
		super();
		name = new Label("Name: ---");
		addComponent(name);
	}

	public void setName(String labelName) {
		name.setValue("Name: "+labelName);
	}
}
