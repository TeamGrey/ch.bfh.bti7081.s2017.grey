package ch.bfh.bti7081.s2017.grey;

import javax.persistence.NoResultException;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

public class TodoListModel {
	private Appointment appointment;
	private TaskService taskService = null;
	
	public TodoListModel(Appointment appointment){
		this.appointment = appointment;
	}
    
    private void singleton(){
    	if (taskService == null){
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl();
        }
    }

    public Boolean createTask(String name) {
    	singleton();
        try {
            taskService.createTask(name, appointment);
        } catch (NoResultException ex){
            return false;
        }
		return true;
    }
}
