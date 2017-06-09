package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import java.util.List;

/**
 * @Author Quentin
 */
public interface TaskService {
    Task findTaskById(long id);
    List<Task> getAllTasks();

    Task createTask(String name, Appointment appointment);
    void removeTask(Task task);

    void addDrugToTask(Task task, Drug drug, int amount, String units);
    List<Task> getTasksByAppointment(Appointment appointment);
    void setDuration(Task task, int amount);
    void addToDuration(Task task, int amount);
    void removeFromDuration(Task task, int amount);
    void toggleActiveStatus(Task task, Boolean status);

    void setFinishedStatus(Task task, Boolean status);
}
