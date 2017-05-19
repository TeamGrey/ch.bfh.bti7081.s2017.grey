package ch.bfh.bti7081.s2017.grey;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PatientTabs extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PatientTabs";

	public PatientTabs(){
		TabSheet content = new TabSheet();
		content.addStyleName("centered-tabs");
		content.addStyleName("equal-width-tabs");
		content.setResponsive(true);
		
		Label clientTempLabel = new Label("Personeninformationen sind hier", ContentMode.HTML);
		VerticalLayout client = new VerticalLayout(clientTempLabel);
		content.addTab(client, "Client");
		
		Label drugsTempLabel = new Label("Inhalt für alle Drogen ;-)", ContentMode.HTML);
		VerticalLayout drugs = new VerticalLayout(drugsTempLabel);
		content.addTab(drugs, "Drugs");
		
		Label toDoTempLabel = new Label("Hier werden die ToDos hinkommen", ContentMode.HTML);
		VerticalLayout toDo = new VerticalLayout(toDoTempLabel);
		content.addTab(toDo, "ToDo");
		
		Design design = new Design();
		addComponent(design.insertContent(content));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		PatientTabsPresenter.loggedIn();
	}
}