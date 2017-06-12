package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.TaskDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @Author Quentin
 */
public class TaskServiceImpl implements TaskService {

    private TaskDao dao;

    public TaskServiceImpl () {
        dao = new TaskDao();
    }

    /**
     * Searches a task by it's id and returns the entity
     * @param id Id of the task
     * @return Task entity if found
     */
    @Override
    public Task findTaskById(long id){
        return dao.find(id);
    }

    /**
     * Creates a new task
     * @param name Name of the task
     * @param appointment Appointment to which the task belongs
     * @return Created task
     */
    @Override
    public Task createTask(String name, Appointment appointment) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        Task task = new Task();
        task.setName(name);
        task.setAppointment(appointment);
        task.setCreated(timestamp);
        task.setChanged(timestamp);

        dao.create(task);
        return task;
    }

    /**
     * Adds a drug to a task
     * @param task Task to be edited
     * @param drug Drug to be added
     * @param amount Amount of the drug
     * @param units Units of the amount
     */
    @Override
    public void addDrugToTask(Task task, Drug drug, int amount, String units) {
        dao.addDrugToTask(task, drug, amount, units);
    }

    /**
     * Returns all tasks
     * @return List of tasks
     */
    @Override
    public List<Task> getAllTasks() {
        return dao.findAll();
    }

    /**
     * Find all tasks for an appointment
     * @param appointment Appointment to which the tasks belong
     * @return List of tasks
     */
    @Override
    public List<Task> getTasksByAppointment(Appointment appointment) {
        return dao.getTasksByAppointment(appointment);
    }

    /**
     * Set the duration of the task
     * @param task Task to be edited
     * @param amount Duration of the task in minutes
     */
    @Override
    public void setDuration(Task task, int amount) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        task.setDuration(amount);
        task.setChanged(timestamp);

        dao.update(task);
    }

    /**
     * Increase the duration of the task
     * @param task Task to be edited
     * @param amount Minutes to be added to the duration
     */
    @Override
    public void addToDuration(Task task, int amount) {
        int newAmount = task.getDuration() + amount;
        setDuration(task, newAmount);
    }

    /**
     * Decrease the duration of the task
     * @param task Task to be edited
     * @param amount Minutes to be removed from the duration
     */
    @Override
    public void removeFromDuration(Task task, int amount) {
        int newAmount = task.getDuration() - amount;
        setDuration(task, newAmount);
    }

    /**
     * Switches the active status of the task
     * @param task Task to be edited
     * @param status new status
     */
    @Override
    public void toggleActiveStatus(Task task, Boolean status) {
    	Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        task.toggleFinished(status);
        task.setChanged(timestamp);    	
    	dao.update(task);
    }

    @Override
    public void setFinishedStatus(Task task, Boolean status) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        task.setFinished(status);
        task.setChanged(timestamp);
        dao.update(task);
    }

    /**
     * Removes a task
     * @param task task to be removed
     */
    @Override
    public void removeTask(Task task) {
        dao.delete(task.getId());
    }
}
