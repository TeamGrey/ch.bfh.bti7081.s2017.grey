package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This Window acts as a Modal for editing
 * the Selection of Habits
 */
public class PatientHabitWindow {

  private List<Habit> addHabits = new ArrayList<>();
  private List<Habit> removeHabits = new ArrayList<>();
  private List<Habit> beforeSelection = new ArrayList<>();
  private List<Habit> afterSelection = new ArrayList<>();
  private List<PatientViewListener> listeners = new ArrayList<>();
  private Grid<Habit> habitSelector = new Grid<>();
  private boolean reactChanges = false;
  private Window habitWindow = new Window("Gewohnheiten");

  /**
   * setup GUI and register event handlers
   */
  public PatientHabitWindow() {
    habitWindow.setWidth("100%");
    habitWindow.setModal(true);

    FormLayout habitInfo = new FormLayout();
    Button closeHabitWindow = new Button("Übernehmen");

    habitInfo.addComponents(habitSelector, closeHabitWindow);
    habitWindow.setContent(habitInfo);

    closeHabitWindow.addClickListener((event) -> close());

    MultiSelectionModel<Habit> habitMultiSelectionModel = (MultiSelectionModel<Habit>) habitSelector
        .setSelectionMode(Grid.SelectionMode.MULTI);
    habitSelector.addColumn(Habit::getName).setCaption("Gewohnheit");

    habitMultiSelectionModel.addMultiSelectionListener(event -> {
      if (!reactChanges) {
        return;
      }
      afterSelection.clear();
      afterSelection.addAll(event.getNewSelection());

      // save added habit relations
      addHabits.clear();
      for (Habit habit : afterSelection) {
        if (!beforeSelection.contains(habit)) {
          addHabits.add(habit);
        }
      }

      // delete removed habit relations
      removeHabits.clear();
      for (Habit habit : beforeSelection) {
        if (!afterSelection.contains(habit)) {
          removeHabits.add(habit);
        }
      }
    });
  }

  void addListener(PatientViewListener listener) {
    listeners.add(listener);
  }

  /**
   * open list of habit options for selection
   *
   * @param habitOptions list of habits to select from
   * @param selected list of already selected habits
   */
  void open(List<Habit> habitOptions, List<Habit> selected) {
    beforeSelection.clear();
    beforeSelection.addAll(selected);
    afterSelection.clear();
    afterSelection.addAll(selected);

    habitSelector.setItems(habitOptions);
    for (Habit habit : selected) {
      habitSelector.select(habit);
    }

    UI.getCurrent().addWindow(habitWindow);
    reactChanges = true;
  }

  /**
   * close window and trigger change event
   */
  void close() {
    UI.getCurrent().removeWindow(habitWindow);
    reactChanges = false;

    for (PatientViewListener listener : listeners) {
      listener.onHabitSelectionChange(addHabits, removeHabits, afterSelection);
    }
  }
}