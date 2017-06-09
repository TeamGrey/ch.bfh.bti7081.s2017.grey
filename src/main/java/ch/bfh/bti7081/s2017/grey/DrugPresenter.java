package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.listener.CheckListener;
import ch.bfh.bti7081.s2017.grey.service.DrugService;
import ch.bfh.bti7081.s2017.grey.service.TaskService;
import ch.bfh.bti7081.s2017.grey.service.impl.DrugServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.TaskServiceImpl;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugPresenter extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private DrugListView drugListView;
    private List<DrugItemView> drugItemViews;
    private TaskService taskService;
    private DrugService drugService;
    private Appointment appointment;
    private Button createButton;

    public DrugPresenter(Appointment appointment) {
        this.appointment = appointment;
        drugListView = new DrugListViewImpl();
        drugItemViews = new LinkedList<>();
        taskService = new TaskServiceImpl();
        drugService = new DrugServiceImpl();

        update();

        addComponent(drugListView);

        createButton = new Button("New Task");
        addComponent(createButton);

        createButton.addClickListener(event -> createDrugTask());
    }

    public void update() {
        try {
            // clear list
            drugListView.clear();
            drugItemViews.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // refresh list
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            List<DrugTaskAssociation> drugTaskAssociations = task.getDrugs();
            for (DrugTaskAssociation drugTaskAssociation : drugTaskAssociations) {
                Drug drug = drugTaskAssociation.getDrug();
                DrugItemView drugView = new DrugItemViewImpl();
                drugView.setName(drug.getName());
                drugView.setAmount(drugTaskAssociation.getAmount());
                drugView.setUnit(drugTaskAssociation.getAmountUnits());
                drugListView.addDrug(drugView);

                drugView.addDeleteListener(() -> {
                    drugTaskAssociations.remove(drugTaskAssociation);
                    taskService.removeTask(task);
                    update();
                });

                drugView.addCheckListener(new CheckListener() {
                    @Override
                    public void check() {
                        taskService.setFinishedStatus(task, true);
                    }

                    @Override
                    public void uncheck() {
                        taskService.setFinishedStatus(task, false);
                    }
                });
            }
        }
    }

    public void createDrugTask() {
        DrugTaskFormView form = new DrugTaskFormViewImpl();

        form.setDrugList(drugService.getAllDrugs());

        form.addCloseListener(() -> {
            Task task;
            if (form.getTaskId() != null && form.getTaskId() >= 0) {
                task = taskService.findTaskById(form.getTaskId());
                task.setName(form.getTaskName());
            } else {
                task = taskService.createTask(form.getTaskName(), appointment);
            }
            taskService.addDrugToTask(task, form.getDrug(), form.getAmount(), form.getAmountUnit());

            update();
        });

        form.open();
    }
}
