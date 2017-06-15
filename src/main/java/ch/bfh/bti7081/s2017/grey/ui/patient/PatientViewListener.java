package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import java.util.List;

/**
 * Created by Joel on 15.06.2017.
 */
public interface PatientViewListener {

  void viewEntered(String view);

  void onDrugSelectionChange(List<Drug> added, List<Drug> removed, List<Drug> selected);

  void onEMCAddition(String firstName, String lastName, String phone, long id);

  void onHabitSelectionChange(List<Habit> added, List<Habit> removed, List<Habit> selected);
}
