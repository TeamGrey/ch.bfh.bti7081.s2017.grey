package ch.bfh.bti7081.s2017.grey.view;

import ch.bfh.bti7081.s2017.grey.listener.DrugTaskListViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugTaskListViewImpl extends VerticalLayout implements DrugTaskListView {
    private List<DrugTaskListViewListener> listeners;
    private VerticalLayout container;
    private List<DrugTaskView> drugTaskViews;
    private Button createButton;

    public DrugTaskListViewImpl() {
        drugTaskViews = new LinkedList<>();
        listeners = new ArrayList<>();

        container = new VerticalLayout();
        addComponent(container);

        createButton = new Button("New Task");
        addComponent(createButton);
        createButton.addClickListener(event -> onCreateTask());

    }

    private void onCreateTask() {
        for (DrugTaskListViewListener listener : listeners) {
            listener.onCreateTask();
        }
    }

    @Override
    public void addDrugTaskListViewListener(DrugTaskListViewListener listener) {

        listeners.add(listener);
    }

    public void addDrug(DrugTaskView drugTaskView) {
        drugTaskViews.add(drugTaskView);
        container.addComponent(drugTaskView);
    }

    public void removeDrug(DrugTaskView drugTaskView) {
        drugTaskViews.remove(drugTaskView);
        container.removeComponent(drugTaskView);
    }

    @Override
    public void clear() {
        drugTaskViews.clear();
        container.removeAllComponents();
    }
}
