package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public interface PatientView {

  void setPatient(Patient patient);

  void setEmContact(List<EmergencyContact> emContact);

  void setDrugList(List<Drug> drugList);

  void setHabitList(List<Habit> habitList);

  void addListener(PatientViewListener listener);

  void setDrugOptions(List<Drug> allDrugs);

  void setHabitOptions(List<Habit> allHabits);
}
