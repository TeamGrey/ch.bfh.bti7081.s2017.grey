package ch.bfh.bti7081.s2017.grey;

import javax.persistence.NoResultException;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

public class TodoModel {
	private TaskService taskService = null;
	private Task task;
    
    private void singleton(){
    	if (taskService == null){
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl();
        }
    }
    
    public void setTask(Task task){
    	this.task = task;
    }

    public Boolean setDuration(int duration) {
        singleton();
        try {
            taskService.setDuration(task, duration);
        } catch (NoResultException ex){
            return false;
        }
        return true;
    }
    
    public Boolean toggleStatus(Boolean status) {
    	singleton();
        try {
            taskService.toggleActiveStatus(task, status);
        } catch (NoResultException ex){
            return false;
        }
        return true;
    }
    
    public Boolean createTask(String name, Appointment appointment) {
    	singleton();
        try {
            taskService.createTask(name, appointment);
        } catch (NoResultException ex){
            return false;
        }
		return true;
    }

	public Boolean removeTask() {
		singleton();
		try {
            taskService.removeTask(task);
        } catch (NoResultException ex){
            return false;
        }
		return true;
		
	}

	public int getTime() {
		return task.getDuration();
	}

}
