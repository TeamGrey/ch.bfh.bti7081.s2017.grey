package ch.bfh.bti7081.s2017.grey.ui.tabs;

import ch.bfh.bti7081.s2017.grey.ui.Design;
import ch.bfh.bti7081.s2017.grey.ui.MyUI;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.ui.drug.DrugTaskModelCollection;
import ch.bfh.bti7081.s2017.grey.ui.patient.PatientModel;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;
import ch.bfh.bti7081.s2017.grey.ui.drug.DrugTaskListView;
import ch.bfh.bti7081.s2017.grey.ui.drug.DrugTaskListViewImpl;
import ch.bfh.bti7081.s2017.grey.ui.drug.DrugTaskPresenter;
import ch.bfh.bti7081.s2017.grey.ui.patient.PatientPresenter;
import ch.bfh.bti7081.s2017.grey.ui.patient.PatientViewImpl;
import ch.bfh.bti7081.s2017.grey.ui.todo.TodoListPresenter;
import ch.bfh.bti7081.s2017.grey.ui.todo.TodoListView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;

/**
 * @author Ken
 */
public class PatientTabsPresenter extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public final static String NAME = "PatientTabs";
	private TodoListView toDo;
	private Appointment appointment;
	private Patient patient;
	private PatientTabs patientTab = new PatientTabs();
	private DrugTaskModelCollection drugTaskModelCollection;
	private PatientPresenter patientPresenter;
	
	/**
	 * Create presenter with all its components
	 */
	public PatientTabsPresenter(){
		patientTab.clearTabs();
		patientTab.setSizeFull();
		
		PatientModel patientModel = new PatientModel();
        PatientViewImpl patientView = new PatientViewImpl();
		patientPresenter = new PatientPresenter(patientView, patientModel);
		patientTab.addTab((Component) patientPresenter.getView(), "Patient");

		drugTaskModelCollection = new DrugTaskModelCollection(appointment);
		DrugTaskListView drugTaskListView = new DrugTaskListViewImpl();
		DrugTaskPresenter drugTaskPresenter = new DrugTaskPresenter(drugTaskListView, drugTaskModelCollection);
		patientTab.addTab(drugTaskListView, "Drugs");

		toDo = todoTab();
		patientTab.addTab((Component) toDo, "ToDo");
	}
	
	private TodoListView todoTab(){
		TodoListPresenter toDo = new TodoListPresenter(this, appointment);

		TaskService taskservice = new TaskServiceImpl();
		List<Task> tasks = taskservice.getTasksByAppointment(appointment);
		for (Task task: tasks){
			toDo.getView().addTask(task);
		}

		toDo.getView().addNewTaskButton();
		return toDo.getView();
	}
	
	/**
	 * Update the Todo-tab
	 */
	public void updateTodoTab(){
		TodoListView newTodo = todoTab();
		patientTab.replaceTab((Component)toDo, (Component)newTodo);
		toDo = newTodo;
	}

	/**
	 * @param appointment Appointment to be set for all components
	 */
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
		drugTaskModelCollection.setAppointment(appointment);
		patientPresenter.setPatient(appointment.getPatient());
		updateTodoTab();
	}

	/**
	 * @param patient Patient to be set for all components
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		MyUI.loggedIn();

		Design design = new Design();
		addComponent(design.insertContent(patientTab, true));
	}
}
