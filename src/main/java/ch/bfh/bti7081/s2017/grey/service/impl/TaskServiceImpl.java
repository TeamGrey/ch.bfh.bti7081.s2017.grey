package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.TaskDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class TaskServiceImpl implements TaskService {

  private TaskDao dao;

  public TaskServiceImpl(EntityManager em) {
    dao = new TaskDao(em);
  }

  /**
   * @see TaskService#findTaskById(long)
   */
  @Override
  public Task findTaskById(long id) {
    return dao.find(id);
  }

  /**
   * @see TaskService#createTask(String, Appointment)
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
   * @see TaskService#addDrugToTask(Task, Drug, int, String)
   */
  @Override
  public void addDrugToTask(Task task, Drug drug, int amount, String units) {
    dao.addDrugToTask(task, drug, amount, units);
  }

  /**
   * @see TaskService#getAllTasks()
   */
  @Override
  public List<Task> getAllTasks() {
    return dao.findAll();
  }

  /**
   * @see TaskService#getTasksByAppointment(Appointment)
   */
  @Override
  public List<Task> getTasksByAppointment(Appointment appointment) {
    return dao.getTasksByAppointment(appointment);
  }

  /**
   * @see TaskService#setDuration(Task, int)
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
   * @see TaskService#addToDuration(Task, int)
   */
  @Override
  public void addToDuration(Task task, int amount) {
    int newAmount = task.getDuration() + amount;
    setDuration(task, newAmount);
  }

  /**
   * @see TaskService#removeFromDuration(Task, int)
   */
  @Override
  public void removeFromDuration(Task task, int amount) {
    int newAmount = task.getDuration() - amount;
    setDuration(task, newAmount);
  }

  /**
   * @see TaskService#toggleActiveStatus(Task, Boolean)
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
   * @see TaskService#removeTask(Task)
   */
  @Override
  public void removeTask(Task task) {
    dao.delete(task.getId());
  }
}
