package ch.bfh.bti7081.s2017.grey.ui.todo;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ken
 */
public class TodoViewImpl extends HorizontalLayout implements TodoView {

  private static final long serialVersionUID = 1L;

  private List<TodoViewListener> listeners = new ArrayList<>();
  private CustomLayout tasklayout = new CustomLayout("tasklayout");
  private Label taskDesc = null;
  private CheckBox checkbox = null;
  private HorizontalLayout taskRight = null;
  private Label estimate = null;
  private Button addTime = null;
  private Button removeTime = null;
  private Button removeAppointment = null;

  /**
   * Create view with all its components
   */
  public TodoViewImpl() {
    //Task left
    checkbox = new CheckBox("", false);
    checkbox.addValueChangeListener(e -> {
      for (TodoViewListener listener : listeners) {
        listener.toggleStatus(checkbox.getValue());
      }
    });
    tasklayout.addComponent(checkbox, "task-left");

    //Task center
    taskDesc = new Label("Task Name");
    tasklayout.addComponent(taskDesc, "task-center");

    //Task right
    taskRight = new HorizontalLayout();
    estimate = new Label("5 min");
    taskRight.addComponent(estimate);
    addTime = new Button("+ 5 min");
    taskRight.addComponent(addTime);
    removeTime = new Button("- 5 min");
    taskRight.addComponent(removeTime);
    removeAppointment = new Button("x");
    taskRight.addComponent(removeAppointment);

    addTime.addClickListener(e -> {
      for (TodoViewListener listener : listeners) {
        listener.addToEstimate(5);
      }
    });
    removeTime.addClickListener(e -> {
      for (TodoViewListener listener : listeners) {
        listener.removeFromEstimate(5);
      }
    });

    removeAppointment.addClickListener(e -> {
      for (TodoViewListener listener : listeners) {
        listener.removeTask();
      }
    });
    tasklayout.addComponent(taskRight, "task-right");

    //Task Layout
    tasklayout.setSizeFull();
    addComponent(tasklayout);
    setSizeFull();
  }

  @Override
  public void setName(String labelName) {
    taskDesc.setValue(labelName);
  }

  @Override
  public void setStatus(boolean status) {
    checkbox.setValue(status);
  }

  @Override
  public void setEstimate(int estimateTime) {
    estimate.setValue(estimateTime + " min");
  }

  @Override
  public void addListener(TodoViewListener listener) {
    listeners.add(listener);
  }
}
