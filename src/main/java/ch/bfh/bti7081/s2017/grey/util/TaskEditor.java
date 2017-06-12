package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
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

  private static void singleton() {
    if (taskService == null) {
      // kind of singleton pattern for taskService
      taskService = new TaskServiceImpl(EntityManagerSingleton.getInstance());
    }
  }

  public static Boolean setDuration(Task task, int duration) {
    singleton();
    try {
      taskService.setDuration(task, duration);
    } catch (NoResultException ex) {
      return false;
    }
    return true;
  }

  public static Boolean toggleStatus(Task task, Boolean status) {
    singleton();
    try {
      taskService.toggleActiveStatus(task, status);
    } catch (NoResultException ex) {
      return false;
    }
    return true;
  }

  public static Boolean createTask(String name, Appointment appointment) {
    singleton();
    try {
      taskService.createTask(name, appointment);
    } catch (NoResultException ex) {
      return false;
    }
    return true;
  }

  public static Boolean removeTask(Task task) {
    singleton();
    try {
      taskService.removeTask(task);
    } catch (NoResultException ex) {
      return false;
    }
    return true;

  }
}
