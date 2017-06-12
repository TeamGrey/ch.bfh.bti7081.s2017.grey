package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

/**
 * @author Joel
 */
public class DrugTaskModel {
    private Task task;
    private DrugTaskAssociation asoc;
    private TaskService taskService;

    DrugTaskModel(Task task) {
        this(task, task.getDrugs().get(0));
    }

    DrugTaskModel(Task task, DrugTaskAssociation asoc) {
        taskService = new TaskServiceImpl();
        this.task = task;
        this.asoc = asoc;
    }

    Task getTask() {
        return task;
    }

    public boolean isFinished() {
        return task.isFinished();
    }

    public void setFinished(boolean finished) {
        taskService.setFinishedStatus(task, finished);
    }

    public String getName() {
        return task.getName();
    }

    public Drug getDrug() {
        return this.asoc.getDrug();
    }

    public int getAmount() {
        return asoc.getAmount();
    }

    public String getUnit() {
        return asoc.getAmountUnits();
    }
}
