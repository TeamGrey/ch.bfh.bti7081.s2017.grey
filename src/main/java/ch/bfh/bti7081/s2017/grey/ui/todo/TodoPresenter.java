package ch.bfh.bti7081.s2017.grey.ui.todo;

import ch.bfh.bti7081.s2017.grey.ui.tabs.PatientTabsPresenter;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

public class TodoPresenter implements TodoView.TodoViewListener {
	private PatientTabsPresenter patientTabsPresenter;
    private TodoModel todoModel;
    private TodoView todoView;

    public TodoPresenter(PatientTabsPresenter patientTabsPresenter, Task task) {
    	this.patientTabsPresenter = patientTabsPresenter;
        todoModel = new TodoModel();
        todoModel.setTask(task);
        todoView = new TodoViewImpl();
        todoView.addListener(this);
        todoView.setName(task.getName());
        todoView.setEstimate(task.getDuration());
        todoView.setStatus(task.isFinished());
    }
	
    public TodoView getView(){
    	return todoView;
    }
	
    @Override
	public void removeTask(){
		todoModel.removeTask();
		patientTabsPresenter.updateTodoTab();
	}
	
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
	
	@Override
	public void addToEstimate(int time){
		int estimateTime;
		estimateTime = todoModel.getTime() + time;
		todoModel.setDuration(estimateTime);
		todoView.setEstimate(estimateTime);
	}
	
	@Override
	public void toggleStatus(boolean status){
		todoModel.toggleStatus(status);
	}

}
