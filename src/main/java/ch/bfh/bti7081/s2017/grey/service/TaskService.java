package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import java.util.List;

/**
 * @Author Quentin
 */
public interface TaskService {
    Task findTaskById(long id);

    void createTask(String name);

    void addDrugsToTask(Task task, List<Drug> drugs, int amount, String units);
}
