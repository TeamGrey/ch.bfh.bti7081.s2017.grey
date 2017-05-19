package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

public class PatientTabs extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private TabSheet content = new TabSheet();

	public PatientTabs(){
		content.addStyleName("centered-tabs");
		content.addStyleName("equal-width-tabs");
		content.setResponsive(true);
		addComponent(content);
	}
	
	public void addTab(Component view, String tabName){
		content.addTab(view, tabName);
	}
}