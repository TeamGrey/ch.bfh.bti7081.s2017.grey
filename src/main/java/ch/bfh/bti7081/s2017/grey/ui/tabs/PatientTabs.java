package ch.bfh.bti7081.s2017.grey.ui.tabs;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

/**
 * @author Ken
 */
public class PatientTabs extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private TabSheet content = new TabSheet();

	/**
	 * Create view with all its components
	 */
	public PatientTabs(){
		content.addStyleName("centered-tabs");
		content.addStyleName("equal-width-tabs");
		content.setSizeFull();
		addComponent(content);
	}
	
	/**
	 * Ad a tab to the tabs
	 * @param view View of the tab
	 * @param tabName Name of the tab
	 */
	public void addTab(Component view, String tabName){
		content.addTab(view, tabName);
	}
	
	/**
	 * Replace the tab with another
	 * @param oldComponent To be replaced view
	 * @param newComponent New View
	 */
	public void replaceTab(Component oldComponent, Component newComponent){
		content.replaceComponent(oldComponent, newComponent);
	}

	/**
	 * Remove all tabs
	 */
	public void clearTabs() {
		content.removeAllComponents();
	}
}