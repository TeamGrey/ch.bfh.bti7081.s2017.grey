package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.exeption.MissingAppointmentException;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskFormViewListener;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskListViewListener;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskViewListener;
import ch.bfh.bti7081.s2017.grey.listener.ModelCollectionListener;
import ch.bfh.bti7081.s2017.grey.model.DrugTaskModel;
import ch.bfh.bti7081.s2017.grey.model.DrugTaskModelCollection;
import ch.bfh.bti7081.s2017.grey.view.*;
import com.vaadin.ui.Notification;

import java.util.List;

/**
 * @author Joel
 */
public class DrugTaskPresenter implements DrugTaskListViewListener, DrugTaskFormViewListener, ModelCollectionListener {
    private DrugTaskListView drugTaskListView;
    private DrugTaskModelCollection drugTaskModelCollection;
    private DrugTaskFormView drugTaskFormView;

    public DrugTaskPresenter(DrugTaskListView drugTaskListView, DrugTaskModelCollection drugTaskModelCollection) {
        drugTaskListView.addDrugTaskListViewListener(this);
        drugTaskModelCollection.addModelCollectionListner(this);
        this.drugTaskModelCollection = drugTaskModelCollection;
        this.drugTaskListView = drugTaskListView;
        try {
            update();
        } catch (MissingAppointmentException e) {
            e.printStackTrace();

            clear();
        }
    }

    public void clear() {
        try {
            // clear list
            drugTaskListView.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update() throws MissingAppointmentException {
        clear();

        // refresh list
        List<DrugTaskModel> drugTaskModels = drugTaskModelCollection.getDrugTasks();
        for (DrugTaskModel model : drugTaskModels) {
            DrugTaskView view = new DrugTaskViewImpl();
            DrugTaskViewListener listener = new DrugTaskViewListener() {
                @Override
                public void onFinishTask() {
                    model.setFinished(true);
                }

                @Override
                public void onUnfinishTask() {
                    model.setFinished(false);
                }

                @Override
                public void onRemoveTask() {
                    drugTaskModelCollection.removeDrugTask(model);
                }
            };
            view.addDrugTaskViewListener(listener);

            view.setFinished(model.isFinished());
            view.setName(model.getName());
            view.setAmount(model.getAmount());
            view.setUnit(model.getUnit());

            drugTaskListView.addDrug(view);
        }
    }

    @Override
    public void onCreateTask() {
        drugTaskFormView = new DrugTaskFormViewImpl();
        drugTaskFormView.addDrugTaskFormViewListener(this);
        drugTaskFormView.setDrugList(drugTaskModelCollection.getDrugs());
    }

    @Override
    public void onFormClosed() {
        // TODO handle case where task is only edited
        try {
            drugTaskModelCollection.addDrugTask(drugTaskFormView.getTaskName(), drugTaskFormView.getDrug(), drugTaskFormView.getAmount(), drugTaskFormView.getAmountUnit());
        } catch (MissingAppointmentException e) {
            e.printStackTrace();

            Notification.show("Error", "Missing Appointement", Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void onCollectionChanged() {
        try {
            update();
        } catch (MissingAppointmentException e) {
            e.printStackTrace();

            clear();
        }
    }
}
