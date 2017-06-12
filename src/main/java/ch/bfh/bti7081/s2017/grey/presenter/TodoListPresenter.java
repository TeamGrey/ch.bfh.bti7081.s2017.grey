package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.view.TodoListView;
import ch.bfh.bti7081.s2017.grey.view.TodoListViewImpl;
import ch.bfh.bti7081.s2017.grey.model.TodoListModel;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;

/**
 * @author Ken
 */
public class TodoListPresenter implements TodoListView.TodoListViewListener {
    private TodoListModel todoListModel;
    private TodoListView todoListView;
	
	/**
	 * Create presenter and link its View and model
	 * @param appointment Appointment for this presenter
	 */
	public TodoListPresenter(Appointment appointment){
		//"The lazy way" -> Implementation like Drugs or Appointment should be made, but too little time...
		todoListModel = new TodoListModel(appointment);
        todoListView = new TodoListViewImpl();
        todoListView.addListener(this);
	}
	
	/**
	 * @return Attached view of this presenter
	 */
	public TodoListView getView(){
    	return todoListView;
    }
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.grey.view.TodoListView.TodoListViewListener#saveNewTask(java.lang.String, com.vaadin.ui.Window)
	 */
	public void saveNewTask(String taskName, Window window){
		todoListModel.createTask(taskName);
    	todoListView.closeWindow(window);
    	PatientTabsPresenter.updateTodoTab();
    }

}
