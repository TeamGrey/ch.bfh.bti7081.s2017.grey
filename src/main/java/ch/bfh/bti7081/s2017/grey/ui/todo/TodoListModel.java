package ch.bfh.bti7081.s2017.grey.ui.todo;

import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import javax.persistence.NoResultException;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

/**
 * @author Ken
 */
public class TodoListModel {
	private Appointment appointment;
	private TaskService taskService = null;
	
	/**
	 * Create the model and set the appointment
	 * @param appointment Appointment for this model
	 */
	public TodoListModel(Appointment appointment){
		this.appointment = appointment;
	}
    
    private void singleton(){
    	if (taskService == null){
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl(EntityManagerSingleton.getInstance());
        }
    }

    /**
     * Create following task
     * @param name Name of the task
     * @return If the creation was successful
     */
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
