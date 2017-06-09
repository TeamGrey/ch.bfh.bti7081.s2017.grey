package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class PatientTabsPresenter extends HorizontalLayout implements View {
	public static final String NAME = "PatientTabs";

	private static TodoListView toDo;
	private static final long serialVersionUID = 1L;
	
	private static PatientTabs patientTab = new PatientTabs();
	
	public PatientTabsPresenter(){
		PatientService patientService = new PatientServiceImpl();
		AppointmentService appointmentService = new AppointmentServiceImpl();
		// TODO fetch patient dynamically
		Patient patient = patientService.getPatientById(4);
		Appointment appointment = appointmentService.getAppointmentById(1);
		patientTab.setSizeFull();

		patientTab.clearTabs();
		
		Label clientTempLabel = new Label("Personeninformationen sind hier", ContentMode.HTML);
		VerticalLayout client = new VerticalLayout(clientTempLabel);
		patientTab.addTab(client, "Patient");

		DrugPresenter drugListView = new DrugPresenter(appointment);
		patientTab.addTab(drugListView, "Drugs");

		toDo = todoTab();
		patientTab.addTab((Component)toDo, "ToDo");
				
		Design design = new Design();
		addComponent(design.insertContent(patientTab));
	}
	
	private static TodoListView todoTab(){
		AppointmentService appointmentservice = new AppointmentServiceImpl();
		// TODO load selected appointment
		Appointment appointment = appointmentservice.getAppointmentById(1);
		TodoListPresenter toDo = new TodoListPresenter(appointment);

		TaskService taskservice = new TaskServiceImpl();
		List<Task> tasks = taskservice.getTasksByAppointment(appointment);
		for (Task task: tasks){
			toDo.getView().addTask(task);
		}

		toDo.getView().addNewTaskButton();
		return toDo.getView();
	}
	
	public static void updateTodoTab(){
		TodoListView newTodo = todoTab();
		patientTab.replaceTab((Component)toDo, (Component)newTodo);
		toDo = newTodo;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		MyUI.loggedIn();

	}
}
