package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public interface PatientDao extends GenericDao<Patient> {
    public Patient getPatientByName(String firstName, String lastName);
    public void addDrugsToPatient(Patient patient, List<Drug> drugs);
    public void addHabitsToPatient(Patient patient, List<Habit> habits);
}
