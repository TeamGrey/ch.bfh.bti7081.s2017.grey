package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import java.util.List;

/**
 * @Author Quentin
 */
public interface TaskService {
    /**
     * Searches a task by it's id and returns the entity
     *
     * @param id Id of the task
     * @return Task entity if found
     */
    Task findTaskById(long id);
    /**
     * Returns all tasks
     *
     * @return List of tasks
     */
    List<Task> getAllTasks();

    /**
     * Creates a new task
     *
     * @param name Name of the task
     * @param appointment Appointment to which the task belongs
     * @return Created task
     */
    Task createTask(String name, Appointment appointment);
    /**
     * Removes a task
     *
     * @param task task to be removed
     */
    void removeTask(Task task);

    /**
     * Adds a drug to a task
     *
     * @param task Task to be edited
     * @param drug Drug to be added
     * @param amount Amount of the drug
     * @param units Units of the amount
     */
    void addDrugToTask(Task task, Drug drug, int amount, String units);
    /**
     * Find all tasks for an appointment
     *
     * @param appointment Appointment to which the tasks belong
     * @return List of tasks
     */
    List<Task> getTasksByAppointment(Appointment appointment);
    /**
     * Set the duration of the task
     *
     * @param task Task to be edited
     * @param amount Duration of the task in minutes
     */
    void setDuration(Task task, int amount);
    /**
     * Increase the duration of the task
     *
     * @param task Task to be edited
     * @param amount Minutes to be added to the duration
     */
    void addToDuration(Task task, int amount);
    /**
     * Decrease the duration of the task
     *
     * @param task Task to be edited
     * @param amount Minutes to be removed from the duration
     */
    void removeFromDuration(Task task, int amount);
    /**
     * Switches the active status of the task
     *
     * @param task Task to be edited
     * @param status new status
     */
    void toggleActiveStatus(Task task, Boolean status);

    void setFinishedStatus(Task task, Boolean status);
}
