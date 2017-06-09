package ch.bfh.bti7081.s2017.grey.view;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

public class PatientTabs extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private TabSheet content = new TabSheet();

	public PatientTabs(){
		content.addStyleName("centered-tabs");
		content.addStyleName("equal-width-tabs");
		content.setSizeFull();
		addComponent(content);
	}
	
	public void addTab(Component view, String tabName){
		content.addTab(view, tabName);
	}
	
	public void replaceTab(Component oldComponent, Component newComponent){
		content.replaceComponent(oldComponent, newComponent);
	}

	public void clearTabs() {
		content.removeAllComponents();
	}
}