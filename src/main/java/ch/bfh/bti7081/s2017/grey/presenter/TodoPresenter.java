package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.view.TodoView;
import ch.bfh.bti7081.s2017.grey.view.TodoViewImpl;
import ch.bfh.bti7081.s2017.grey.model.TodoModel;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

/**
 * @author Ken
 */
public class TodoPresenter implements TodoView.TodoViewListener {
    private TodoModel todoModel;
    private TodoView todoView;

    /**
     * Create presenter and link its View and model
	 * @param task Task for this presenter
     */
    public TodoPresenter(Task task) {
		//"The lazy way" -> Implementation like Drugs or Appointment should be made, but too little time...
        todoModel = new TodoModel();
        todoModel.setTask(task);
        todoView = new TodoViewImpl();
        todoView.addListener(this);
        todoView.setName(task.getName());
        todoView.setEstimate(task.getDuration());
        todoView.setStatus(task.isFinished());
    }
	
    /**
     * @return Attached view of this presenter
     */
    public TodoView getView(){
    	return todoView;
    }
	
    /* (non-Javadoc)
     * @see ch.bfh.bti7081.s2017.grey.view.TodoView.TodoViewListener#removeTask()
     */
    @Override
	public void removeTask(){
		todoModel.removeTask();
		PatientTabsPresenter.updateTodoTab();
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.grey.view.TodoView.TodoViewListener#removeFromEstimate(int)
	 */
	@Override
	public void removeFromEstimate(int time){
		int estimateTime;
		estimateTime = todoModel.getTime() - time;
		
		if(estimateTime < 5){
			Notification.show("Mindestens 5 min pro To-Do", Notification.Type.WARNING_MESSAGE);
		}
		else{
			todoModel.setDuration(estimateTime);
			todoView.setEstimate(estimateTime);
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.grey.view.TodoView.TodoViewListener#addToEstimate(int)
	 */
	@Override
	public void addToEstimate(int time){
		int estimateTime;
		estimateTime = todoModel.getTime() + time;
		todoModel.setDuration(estimateTime);
		todoView.setEstimate(estimateTime);
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.grey.view.TodoView.TodoViewListener#toggleStatus(boolean)
	 */
	@Override
	public void toggleStatus(boolean status){
		todoModel.toggleStatus(status);
	}

}
