package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.exeption.MissingAppointmentException;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskFormViewListener;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskListViewListener;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskViewListener;
import ch.bfh.bti7081.s2017.grey.listener.ModelCollectionListener;
import com.vaadin.ui.Notification;

import java.util.List;

/**
 * Presenter for Drug Task View
 * populates view with the tasks from the collection
 *
 * @author Joel
 */
public class DrugTaskPresenter implements DrugTaskListViewListener, DrugTaskFormViewListener, ModelCollectionListener {
    private DrugTaskListView drugTaskListView;
    private DrugTaskModelCollection drugTaskModelCollection;
    private DrugTaskFormView drugTaskFormView;

    /**
     * register self as listener to view and model
     *
     * @param drugTaskListView        view
     * @param drugTaskModelCollection model
     */
    public DrugTaskPresenter(DrugTaskListView drugTaskListView, DrugTaskModelCollection drugTaskModelCollection) {
        drugTaskListView.addDrugTaskListViewListener(this);
        drugTaskModelCollection.addModelCollectionListner(this);
        this.drugTaskModelCollection = drugTaskModelCollection;
        this.drugTaskListView = drugTaskListView;
        try {
            update();
        } catch (MissingAppointmentException e) {
            // gracefully handle exception. log it and clear view
            e.printStackTrace();
            clear();
        }
    }

    /**
     * clear view as there is no Task
     */
    public void clear() {
        try {
            // clear list
            drugTaskListView.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * refresh list of Drug Task
     *
     * @throws MissingAppointmentException passing Exception from DrugTaskModelCollection
     */
    public void update() throws MissingAppointmentException {
        clear();

        // refresh list
        List<DrugTaskModel> drugTaskModels = drugTaskModelCollection.getDrugTasks();
        for (DrugTaskModel model : drugTaskModels) {
            // create view for model and add it to the list
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

    /**
     * onCreateTask event from DrugTaskViewList
     * show DrugTaskForm and listen to its event
     */
    @Override
    public void onCreateTask() {
        drugTaskFormView = new DrugTaskFormViewImpl();
        drugTaskFormView.addDrugTaskFormViewListener(this);
        drugTaskFormView.setDrugList(drugTaskModelCollection.getDrugs());
    }

    /**
     * onFormClose event from DrugTaskView
     * create new DrugTask from form data
     */
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

    /**
     * onCollectionChanged event from DrugTaskModeCollection
     * update view
     */
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
