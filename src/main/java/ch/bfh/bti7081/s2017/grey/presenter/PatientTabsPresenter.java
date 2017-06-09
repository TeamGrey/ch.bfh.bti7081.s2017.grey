package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.*;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.model.PatientModel;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;
import ch.bfh.bti7081.s2017.grey.view.PatientTabs;
import ch.bfh.bti7081.s2017.grey.view.PatientViewImpl;
import ch.bfh.bti7081.s2017.grey.view.TodoListView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;

public class PatientTabsPresenter extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;

	public final static String NAME = "PatientTabs";

	private static TodoListView toDo;
	private static Appointment appointment;
	private static Patient patient;
	
	private static PatientTabs patientTab = new PatientTabs();
	
	public PatientTabsPresenter(){
		patientTab.clearTabs();
		patientTab.setSizeFull();
		
		PatientModel patientModel = new PatientModel();
        PatientViewImpl patientView = new PatientViewImpl();
        patientModel.setPatient(patient);
        patientView.setPatient(patient);
		PatientPresenter patientPresenter = new PatientPresenter(patientView, patientModel);
		patientTab.addTab((Component) patientPresenter.getView(), "Patient");

		DrugPresenter drugListView = new DrugPresenter(appointment);
		patientTab.addTab(drugListView, "Drugs");

		toDo = todoTab();
		patientTab.addTab((Component) toDo, "ToDo");
				
		Design design = new Design();
		addComponent(design.insertContent(patientTab));
	}
	
	public void setAppointment(Appointment appointment){
		this.appointment = appointment;
	}
	
	public void setPatient(Patient patient){
		this.patient = patient;
	}
	
	private static TodoListView todoTab(){
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
