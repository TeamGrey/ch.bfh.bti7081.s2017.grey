package ch.bfh.bti7081.s2017.grey;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PatientTabsPresenter extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PatientTabs";
	
	private PatientTabs patientTab = new PatientTabs();
	
	public PatientTabsPresenter(){
		
		Label clientTempLabel = new Label("Personeninformationen sind hier", ContentMode.HTML);
		VerticalLayout client = new VerticalLayout(clientTempLabel);
		patientTab.addTab(client, "Client");
		
		Label drugsTempLabel = new Label("Inhalt für alle Drogen ;-)", ContentMode.HTML);
		VerticalLayout drugs = new VerticalLayout(drugsTempLabel);
		patientTab.addTab(drugs, "Drugs");
		
		Label toDoTempLabel = new Label("Hier werden die ToDos hinkommen", ContentMode.HTML);
		VerticalLayout toDo = new VerticalLayout(toDoTempLabel);
		patientTab.addTab(toDo, "ToDo");
		
		Design design = new Design();
		addComponent(design.insertContent(patientTab));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		MyUI.loggedIn();
	}
}