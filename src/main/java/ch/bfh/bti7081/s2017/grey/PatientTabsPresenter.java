package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.PatientDrugAssociation;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.persistence.EntityManager;

public class PatientTabsPresenter extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PatientTabs";
	private static TaskListView toDo;

	private static EntityManager em = EntityManagerSingleton.getInstance();
	
	private static PatientTabs patientTab = new PatientTabs();
	
	public PatientTabsPresenter(){
		PatientService patientService = new PatientServiceImpl(em);
		// TODO fetch patient dynamicly
		Patient patient = patientService.getPatientById(4);
		patientTab.setSizeFull();
		
		Label clientTempLabel = new Label("Personeninformationen sind hier", ContentMode.HTML);
		VerticalLayout client = new VerticalLayout(clientTempLabel);
		patientTab.addTab(client, "Patient");

		DrugListView drugs = new DrugListView();
		for (PatientDrugAssociation patientDrugAssociation: patient.getDrugs()) {
			drugs.addDrug(patientDrugAssociation.getDrug());
		}
		patientTab.addTab(drugs, "Drugs");

		toDo = todoTab();
		patientTab.addTab(toDo, "ToDo");
				
		Design design = new Design();
		addComponent(design.insertContent(patientTab));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		MyUI.loggedIn();

	}
	
	private static TaskListView todoTab(){
		AppointmentService appointmentservice = new AppointmentServiceImpl(em);
		// TODO load selected appointment
		Appointment appointment = appointmentservice.getAppointmentById(1);
		TaskListView toDo = new TaskListView(appointment);
		toDo.setSizeFull();
		
		TaskService taskservice = new TaskServiceImpl(em);
		List<Task> tasks = taskservice.getTasksByAppointment(appointment);
		for (Task task: tasks){
			toDo.addTask(task);
		}

		toDo.addNewTaskButton();
		return toDo;
	}
	
	public static void updateTodoTab(){
		TaskListView newTodo = todoTab();
		patientTab.replaceTab(toDo, newTodo);
		toDo = newTodo;
	}
}
