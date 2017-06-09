package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.view.TodoView;
import ch.bfh.bti7081.s2017.grey.view.TodoViewImpl;
import ch.bfh.bti7081.s2017.grey.model.TodoModel;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2017.grey.database.entity.Task;

public class TodoPresenter implements TodoView.TodoViewListener {
    private TodoModel todoModel;
    private TodoView todoView;

    public TodoPresenter(Task task) {
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
		PatientTabsPresenter.updateTodoTab();
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
