package ch.bfh.bti7081.s2017.grey.ui.todo;

import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import javax.persistence.NoResultException;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

/**
 * @author Ken
 */
public class TodoModel {
	private TaskService taskService = null;
	private Task task;
    
    private void singleton(){
    	if (taskService == null){
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl(EntityManagerSingleton.getInstance());
        }
    }
    
    /**
     * @param task Task for this model
     */
    public void setTask(Task task){
    	this.task = task;
    }

    /**
     * Set the duration of the current task
     * @param duration Duration of the task
     * @return If the creation was successful
     */
    public void setDuration(int duration) {
        singleton();
        try {
            taskService.setDuration(task, duration);
        } catch (NoResultException ex){
        }
    }
    
    /**
     * Set the Status of the current task
     * @param status Status of the task
     * @return If the creation was successful
     */
    public void toggleStatus(Boolean status) {
    	singleton();
        try {
            taskService.toggleActiveStatus(task, status);
        } catch (NoResultException ex){
        }
    }
    
    /**
     * Create a new task for the following appointment
     * @param name Name of the task
     * @param appointment Appointment to which the task will be linked to
     * @return If the creation was successful
     */
    public Boolean createTask(String name, Appointment appointment) {
    	singleton();
        try {
            taskService.createTask(name, appointment);
        } catch (NoResultException ex){
            return false;
        }
		return true;
    }

	/**
	 * Delete the task
	 * @return If the deletion was successful
	 */
	public void removeTask() {
		singleton();
		try {
            taskService.removeTask(task);
        } catch (NoResultException ex){
        }

    }

	/**
	 * Get the current duration of the task
	 * @return Duration of the task
	 */
	public int getTime() {
		return task.getDuration();
	}

}
