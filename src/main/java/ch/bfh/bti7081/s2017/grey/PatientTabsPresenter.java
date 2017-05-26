package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import java.util.List;

public class PatientTabsPresenter extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PatientTabs";
	
	private PatientTabs patientTab = new PatientTabs();
	
	public PatientTabsPresenter(){
		patientTab.setSizeFull();
		
		Label clientTempLabel = new Label("Personeninformationen sind hier", ContentMode.HTML);
		VerticalLayout client = new VerticalLayout(clientTempLabel);
		patientTab.addTab(client, "Client");
		
		Label drugsTempLabel = new Label("Inhalt für alle Drogen ;-)", ContentMode.HTML);
		VerticalLayout drugs = new VerticalLayout(drugsTempLabel);
		patientTab.addTab(drugs, "Drugs");
		

		// TODO load selected appointment
		TaskListView toDo = new TaskListView();
		Appointment appointment = EntityManagerSingleton.getInstance().find(Appointment.class, (long) 1);
		TaskService taskService = new TaskServiceImpl();
		appointment.setId(1);
		List<Task> tasks = taskService.getTasksByAppointment(appointment);
		toDo.setSizeFull();
		for (Task task: tasks) {
            toDo.addTask(task);            
        }
		toDo.addNewTaskButton();
		patientTab.addTab(toDo, "ToDo");
				
		Design design = new Design();
		addComponent(design.insertContent(patientTab));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		MyUI.loggedIn();
	}
}