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

    @Override
    public Task findTaskById(long id){
        return dao.find(id);
    }

    @Override
    public void createTask(String name, Appointment appointment) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        Task task = new Task();
        task.setName(name);
        task.setAppointment(appointment);
        task.setCreated(timestamp);
        task.setChanged(timestamp);

        dao.create(task);
    }

    @Override
    public void addDrugsToTask(Task task, List<Drug> drugs, int amount, String units) {
        dao.addDrugsToTask(task, drugs, amount, units);
    }

    @Override
    public List<Task> getAllTasks() {
        return dao.findAll();
    }

    @Override
    public List<Task> getTasksByAppointment(Appointment appointment) {
        return dao.getTasksByAppointment(appointment);
    }

    @Override
    public void setDuration(Task task, int amount) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        task.setDuration(amount);
        task.setChanged(timestamp);

        dao.update(task);
    }

    @Override
    public void addToDuration(Task task, int amount) {
        int newAmount = task.getDuration() + amount;
        setDuration(task, newAmount);
    }

    @Override
    public void removeFromDuration(Task task, int amount) {
        int newAmount = task.getDuration() - amount;
        setDuration(task, newAmount);
    }

    @Override
    public void toggleActiveStatus(Task task, Boolean status) {
    	Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        task.toggleFinished(status);
        task.setChanged(timestamp);    	
    	dao.update(task);
    }
    
    @Override
    public void removeTask(Task task) {
        dao.delete(task.getId());
    }
}
