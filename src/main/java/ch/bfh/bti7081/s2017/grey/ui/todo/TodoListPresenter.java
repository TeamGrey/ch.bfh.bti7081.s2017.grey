package ch.bfh.bti7081.s2017.grey.ui.todo;

import ch.bfh.bti7081.s2017.grey.ui.tabs.PatientTabsPresenter;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;

/**
 * @author Ken
 */
public class TodoListPresenter implements TodoListView.TodoListViewListener {
	private PatientTabsPresenter patientTabsPresenter;
    private TodoListModel todoListModel;
    private TodoListView todoListView;
	
	
	/**
	 * Create presenter and link its View and model
	 * @param patientTabsPresenter
	 * @param appointment Appointment for this presenter
	 */
	public TodoListPresenter(PatientTabsPresenter patientTabsPresenter, Appointment appointment){
		//"The lazy way" -> Implementation like Drugs or Appointment should be made, but too little time...
		this.patientTabsPresenter = patientTabsPresenter;
		todoListModel = new TodoListModel(appointment);
        todoListView = new TodoListViewImpl(patientTabsPresenter);
        todoListView.addListener(this);
	}
	
	/**
	 * @return Attached view of this presenter
	 */
	public TodoListView getView(){
    	return todoListView;
    }
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.grey.ui.todo.TodoListView.TodoListViewListener#saveNewTask(java.lang.String, com.vaadin.ui.Window)
	 */
	public void saveNewTask(String taskName, Window window){
		todoListModel.createTask(taskName);
    	todoListView.closeWindow(window);
    	patientTabsPresenter.updateTodoTab();
    }

}
