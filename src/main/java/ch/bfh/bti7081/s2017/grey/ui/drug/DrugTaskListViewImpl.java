package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.listener.DrugTaskListViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
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
  private Label noTasks;
  private int tasks = 0;

  public DrugTaskListViewImpl() {
    drugTaskViews = new LinkedList<>();
    listeners = new ArrayList<>();

    container = new VerticalLayout();
    addComponent(container);

    noTasks = new Label("Keine Tasks vorhanden");
    container.addComponent(noTasks);

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
    tasks++;
    container.removeComponent(noTasks);
    drugTaskViews.add(drugTaskView);
    container.addComponent(drugTaskView);
  }

  public void removeDrug(DrugTaskView drugTaskView) {
    tasks--;
    if (tasks <= 0) {
      container.addComponent(noTasks);
    }
    drugTaskViews.remove(drugTaskView);
    container.removeComponent(drugTaskView);
  }

  @Override
  public void clear() {
    drugTaskViews.clear();
    container.removeAllComponents();
    tasks = 0;
    container.addComponent(noTasks);
  }
}
