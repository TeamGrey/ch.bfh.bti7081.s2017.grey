package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public interface TaskDao extends GenericDao<Task> {
    public List<Task> getTasksByAppointment(Appointment appointment);
    public void addDrugsToTask(Task task, List<Drug> drugs, int amount, String units);
}
