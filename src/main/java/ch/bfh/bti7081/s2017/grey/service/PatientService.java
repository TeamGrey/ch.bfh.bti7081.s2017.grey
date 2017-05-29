package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * @Author Quentin
 */
public interface PatientService {
    public Patient getPatientById(long id);
    public void createPatient(String firstname, String lastname);
    public Patient getPatientByName(String firstName, String lastName);
    public void addDrugsToPatient(Patient patient, List<Drug> drugs);
    public void addHabitsToPatient(Patient patient, List<Habit> habits);
    public List<Patient> getAllPatients();
}
