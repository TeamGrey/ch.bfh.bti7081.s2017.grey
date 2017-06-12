package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.view.TodoListView;
import ch.bfh.bti7081.s2017.grey.view.TodoListViewImpl;
import ch.bfh.bti7081.s2017.grey.model.TodoListModel;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;

public class TodoListPresenter implements TodoListView.TodoListViewListener {
	private PatientTabsPresenter patientTabsPresenter;
    private TodoListModel todoListModel;
    private TodoListView todoListView;
	
	public TodoListPresenter(PatientTabsPresenter patientTabsPresenter, Appointment appointment){
		this.patientTabsPresenter = patientTabsPresenter;
		todoListModel = new TodoListModel(appointment);
        todoListView = new TodoListViewImpl(patientTabsPresenter);
        todoListView.addListener(this);
	}
	
	public TodoListView getView(){
    	return todoListView;
    }
	
	public void saveNewTask(String taskName, Window window){
		todoListModel.createTask(taskName);
    	todoListView.closeWindow(window);
    	patientTabsPresenter.updateTodoTab();
    }

}
