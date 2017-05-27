package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

import javax.persistence.NoResultException;

/**
 * This Util provides functions for the tasks
 *
 * @author Ken
 */
public class TaskEditor {
    private static TaskService taskService = null;

    public static Boolean setDuration(Task task, int duration) {
        if (taskService == null)
        {
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl();
        }
        try {
            taskService.setDuration(task, duration);
        } catch (NoResultException ex){
            return false;
        }
        return true;
    }
    
    public static Boolean toggleStatus(Task task, Boolean status) {
        if (taskService == null)
        {
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl();
        }
        try {
            taskService.toggleActiveStatus(task, status);
        } catch (NoResultException ex){
            return false;
        }
        return true;
    }
    
    public static Boolean createTask(String name, Appointment appointment) {
        if (taskService == null)
        {
            // kind of singleton pattern for taskService
        	taskService = new TaskServiceImpl();
        }
        try {
            taskService.createTask(name, appointment);
        } catch (NoResultException ex){
            return false;
        }
		return true;
    }
}
