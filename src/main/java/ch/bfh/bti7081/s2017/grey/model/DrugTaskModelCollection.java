package ch.bfh.bti7081.s2017.grey.model;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.listener.ModelCollectionListener;
import ch.bfh.bti7081.s2017.grey.service.DrugService;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.DrugServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugTaskModelCollection {
    private List<ModelCollectionListener> listeners;
    private Appointment appointment;
    private TaskService taskService;
    private DrugService drugService;

    public DrugTaskModelCollection(Appointment appointment) {
        listeners = new ArrayList<>();
        this.appointment = appointment;
        taskService = new TaskServiceImpl();
        drugService = new DrugServiceImpl();
    }

    public void addModelCollectionListner(ModelCollectionListener listener) {
        listeners.add(listener);
    }

    private void onChange() {
        for (ModelCollectionListener listener : listeners) {
            listener.onCollectionChanged();
        }
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<DrugTaskModel> getDrugTasks() {
        List<DrugTaskModel> drugTaskModels = new ArrayList<>();

        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            List<DrugTaskAssociation> drugTaskAssociations = task.getDrugs();
            for (DrugTaskAssociation drugTaskAssociation : drugTaskAssociations) {
                DrugTaskModel model = new DrugTaskModel(task, drugTaskAssociation);
                drugTaskModels.add(model);
            }
        }

        return drugTaskModels;
    }

    public DrugTaskModel addDrugTask(String description, Drug drug, int amount, String unit) {
        Task task = taskService.createTask(description, appointment);
        taskService.addDrugToTask(task, drug, amount, unit);
        onChange();
        return new DrugTaskModel(task);
    }

    public void removeDrugTask(DrugTaskModel drugTaskModel) {
        Task task = drugTaskModel.getTask();
        taskService.removeTask(task);
        onChange();
    }

    public List<Drug> getDrugs() {
        return drugService.getAllDrugs();
    }
}
